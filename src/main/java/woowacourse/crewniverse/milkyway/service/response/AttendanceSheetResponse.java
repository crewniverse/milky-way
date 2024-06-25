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
    private final String name;
    private final String campusName;

    public AttendanceSheetResponse(final String rawDate, final String name, final String campusName) {
        this.date = LocalDate.parse(rawDate, DATE_PARSER);
        this.name = name;
        this.campusName = campusName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getCampusName() {
        return campusName;
    }

    public Crew getCrew() {
        return new Crew(name, campusName);
    }
}
