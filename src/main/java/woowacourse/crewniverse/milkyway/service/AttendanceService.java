package woowacourse.crewniverse.milkyway.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowacourse.crewniverse.milkyway.domain.Attendance;
import woowacourse.crewniverse.milkyway.domain.Crew;
import woowacourse.crewniverse.milkyway.repository.AttendanceRepository;
import woowacourse.crewniverse.milkyway.service.response.AttendanceResponse;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponses;

@Service
public class AttendanceService {
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
    public void updateAttendance() {
        final AttendanceSheetResponses attendanceSheetResponses = attendanceCrawler.execute()
                .findByDate(LocalDate.now());
        final List<Attendance> attendances = attendanceRepository.findAll();
        final List<Crew> crews = attendanceSheetResponses.getAttendedCrew();
        attendances.stream()
                .filter(Attendance::isNotAttended)
                .filter(attendance -> crews.contains(attendance.getCrew()))
                .forEach(Attendance::updateAttendedDate);
    }
}
