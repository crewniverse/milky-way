package poke.fromitive.attendance.response;

import poke.fromitive.attendance.domain.Crew;

public record AttendanceResponse(String name) {
    public static AttendanceResponse fromCrew(Crew crew) {
        return new AttendanceResponse(crew.getName());
    }
}
