package woowacourse.crewniverse.milkyway.service.response;

import java.time.LocalDate;
import java.util.List;

public class AttendanceSheetResponses {

    private final List<AttendanceSheetResponse> attendanceSheetResponses;

    public AttendanceSheetResponses(final List<AttendanceSheetResponse> attendanceSheetResponses) {
        this.attendanceSheetResponses = attendanceSheetResponses;
    }
  
    public List<AttendanceSheetResponse> findByDate(LocalDate date) {
        return attendanceSheetResponses.stream()
            .filter(attendanceSheetResponse -> attendanceSheetResponse.getDate().isEqual(date))
            .toList();
    }
}
