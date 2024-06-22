package poke.fromitive.attendance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poke.fromitive.attendance.config.SheetOption;
import poke.fromitive.attendance.domain.Attendance;
import poke.fromitive.attendance.domain.Crew;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;
import poke.fromitive.attendance.repository.AttendanceRepository;
import poke.fromitive.attendance.response.AttendanceResponse;
import poke.fromitive.attendance.response.AttendanceSheetResponses;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final SheetOption sheetOption;
    private final AttendanceCrawler attendanceCrawler;

    public AttendanceService(final AttendanceRepository attendanceRepository,
                             final SheetOption sheetOption,
                             final AttendanceCrawler attendanceCrawler) {
        this.attendanceRepository = attendanceRepository;
        this.sheetOption = sheetOption;
        this.attendanceCrawler = attendanceCrawler;
    }

    public List<AttendanceResponse> getNotAttendanceCrew() {
        return attendanceRepository.findByLastAttendedDateNotOrLastAttendedDateIsNull(LocalDate.now())
                .stream()
                .map(Attendance::getCrew)
                .map(AttendanceResponse::fromCrew).toList();
    }

    @Transactional
    public void updateAttendance() throws GeneralSecurityException, IOException {
        final AttendanceSheetResponses attendanceSheetResponses = attendanceCrawler.execute();
        updateAttendance(attendanceSheetResponses.findByDate(LocalDate.now()));
    }

    private void updateAttendance(final AttendanceSheetResponses attendanceSheetResponses) {
        final List<Attendance> attendances = attendanceRepository.findAll();
        final List<Crew> crews = attendanceSheetResponses.getAttendedCrew();
        attendances.stream()
                .filter(Attendance::isNotAttended)
                .filter(attendance -> crews.contains(attendance.getCrew()))
                .forEach(Attendance::updateAttendedDate);
    }
}
