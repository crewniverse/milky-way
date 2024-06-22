package poke.fromitive.attendance.response;

import poke.fromitive.attendance.domain.Crew;

import java.time.LocalDate;
import java.util.List;

public class AttendanceSheetResponses {
    private final List<AttendanceSheetResponse> attendanceSheetResponses;

    public AttendanceSheetResponses(final List<AttendanceSheetResponse> attendanceSheetResponses) {
        this.attendanceSheetResponses = attendanceSheetResponses;
    }

    public AttendanceSheetResponses findByDate(LocalDate date){
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
