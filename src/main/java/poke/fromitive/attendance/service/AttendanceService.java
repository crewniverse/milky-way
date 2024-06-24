package poke.fromitive.attendance.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poke.fromitive.attendance.domain.Attendance;
import poke.fromitive.attendance.domain.Crew;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;
import poke.fromitive.attendance.repository.AttendanceRepository;
import poke.fromitive.attendance.response.AttendanceResponse;
import poke.fromitive.attendance.response.AttendanceSheetResponses;

@Service
public class AttendanceService {
    private static final Logger log = LoggerFactory.getLogger(AttendanceService.class);

    private final AttendanceRepository attendanceRepository;
    private final AttendanceCrawler attendanceCrawler;

    public AttendanceService(final AttendanceRepository attendanceRepository,
                             final AttendanceCrawler attendanceCrawler) {
        this.attendanceRepository = attendanceRepository;
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
        try {
            final AttendanceSheetResponses attendanceSheetResponses = attendanceCrawler.execute();
            updateAttendance(attendanceSheetResponses.findByDate(LocalDate.now()));
        } catch (SocketTimeoutException e) {
            log.error("허걱! 출석부 불러오다가 타임아웃 걸렸구만유");
        }
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
