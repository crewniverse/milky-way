package woowacourse.crewniverse.milkyway.service.response;

import java.time.LocalDate;
import woowacourse.crewniverse.milkyway.domain.Campus;

public record AttendanceSheetResponse(LocalDate date, String crewName, Campus campus) {

    public AttendanceSheetResponse(final LocalDate date, final String crewName, final String campusName) {
        this(date, crewName, Campus.fromName(campusName));
    }

    public boolean hasDateOf(LocalDate date) {
        return this.date.equals(date);
    }
}
