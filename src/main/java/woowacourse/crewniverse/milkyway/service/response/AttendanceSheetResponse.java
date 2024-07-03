package woowacourse.crewniverse.milkyway.service.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import woowacourse.crewniverse.milkyway.domain.Campus;
import woowacourse.crewniverse.milkyway.domain.Crew;

public class AttendanceSheetResponse {

    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern(
        "yyyy. M. d a h:mm:ss", Locale.KOREA
    );

    private final LocalDate date;
    private final Crew crew;

    public AttendanceSheetResponse(final String rawDate,
                                   final String name, final String campusName) {
        this.date = LocalDate.parse(rawDate, DATE_PARSER);
        final Campus campus = Campus.fromName(campusName);
        this.crew = new Crew(name, campus);
    }

    public LocalDate getDate() {
        return date;
    }

    public Crew getCrew() {
        return crew;
    }

    public boolean isDateOf(final LocalDate date) {
        return this.date.equals(date);
    }
}
