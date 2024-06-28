package woowacourse.crewniverse.milkyway.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import woowacourse.crewniverse.milkyway.domain.Attendance;
import woowacourse.crewniverse.milkyway.domain.Crew;
import woowacourse.crewniverse.milkyway.repository.AttendanceRepository;
import woowacourse.crewniverse.milkyway.repository.CrewRepository;
import woowacourse.crewniverse.milkyway.service.response.AttendanceResponse;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponse;

@Service
public class AttendanceService {

    private final CrewRepository crewRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceCrawler attendanceCrawler;

    public AttendanceService(final CrewRepository crewRepository,
                             final AttendanceRepository attendanceRepository,
                             final AttendanceCrawler attendanceCrawler) {
        this.crewRepository = crewRepository;
        this.attendanceRepository = attendanceRepository;
        this.attendanceCrawler = attendanceCrawler;
    }

    public List<AttendanceResponse> getAbsentCrew() {
        return crewRepository.findByAbsentedCrew(
                LocalDate.now())
            .stream()
            .map(Crew::getName)
            .map(AttendanceResponse::new)
            .toList();
    }

    public void updateAttendance() {
        LocalDate today = LocalDate.now();
        final List<AttendanceSheetResponse> attendanceSheetResponses = attendanceCrawler.execute()
            .findByDate(today);
        final List<Attendance> attendances = createNewAttendances(attendanceSheetResponses, today);
        attendanceRepository.saveAll(attendances);
    }

    private List<Attendance> createNewAttendances(
        final List<AttendanceSheetResponse> attendanceSheetResponses, final LocalDate today) {
        final List<Crew> crews = crewRepository.findAll();
        final List<Crew> attendanceCrews = attendanceSheetResponses.stream()
            .map(AttendanceSheetResponse::getCrew)
            .toList();
        return crews.stream()
            .filter(attendanceCrews::contains)
            .map(crew -> new Attendance(crew, today))
            .toList();
    }
}
