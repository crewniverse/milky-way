package woowacourse.crewniverse.milkyway.service.response;

import woowacourse.crewniverse.milkyway.domain.Crew;

public record AttendanceResponse(String name) {
    public static AttendanceResponse fromCrew(Crew crew) {
        return new AttendanceResponse(crew.getName());
    }
}
