package woowacourse.crewniverse.milkyway.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
        return crewRepository.findAbsentCrewByDate(LocalDate.now())
                .stream()
                .map(Crew::getName)
                .map(AttendanceResponse::new)
                .toList();
    }

    public void updateAttendance() {
        LocalDate today = LocalDate.now();
        final Map<String, AttendanceSheetResponse> todayResponses = attendanceCrawler.getAttendancesDateOf(today)
                .stream()
                .collect(Collectors.toMap(AttendanceSheetResponse::crewName, Function.identity()));
        final List<Crew> absentCrews = crewRepository.findAbsentCrewByDate(today);

        final List<Attendance> newAttendances = absentCrews.stream()
                .filter(crew -> todayResponses.containsKey(crew.getName()))
                .map(crew -> new Attendance(crew, today))
                .toList();

        attendanceRepository.saveAll(newAttendances);
    }
}
