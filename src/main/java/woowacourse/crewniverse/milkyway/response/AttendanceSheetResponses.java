package woowacourse.crewniverse.milkyway.response;

import java.time.LocalDate;
import java.util.List;
import woowacourse.crewniverse.milkyway.domain.Crew;

public class AttendanceSheetResponses {
    private final List<AttendanceSheetResponse> attendanceSheetResponses;

    public AttendanceSheetResponses(final List<AttendanceSheetResponse> attendanceSheetResponses) {
        this.attendanceSheetResponses = attendanceSheetResponses;
    }

    public AttendanceSheetResponses findByDate(LocalDate date) {
        return new AttendanceSheetResponses(attendanceSheetResponses.stream()
                .filter(attendanceSheetResponse -> attendanceSheetResponse.getDate().isEqual(date))
                .toList());
    }

    public List<Crew> getAttendedCrew() {
        return attendanceSheetResponses.stream()
                .map(AttendanceSheetResponse::getCrew)
                .toList();
    }
}
