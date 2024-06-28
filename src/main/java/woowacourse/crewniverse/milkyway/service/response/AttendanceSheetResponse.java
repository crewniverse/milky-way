package woowacourse.crewniverse.milkyway.service.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import woowacourse.crewniverse.milkyway.domain.Crew;

public class AttendanceSheetResponse {

    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern(
        "yyyy. M. d a h:mm:ss", Locale.KOREA
    );

    private final LocalDate date;
    private final Crew crew;

    public AttendanceSheetResponse(final String rawDate, final String name,
                                   final String campusName) {
        this.date = LocalDate.parse(rawDate, DATE_PARSER);
        this.crew = new Crew(name, campusName);
    }

    public LocalDate getDate() {
        return date;
    }

    public Crew getCrew() {
        return crew;
    }
}
