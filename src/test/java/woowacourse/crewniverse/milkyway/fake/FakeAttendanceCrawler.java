package woowacourse.crewniverse.milkyway.fake;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import woowacourse.crewniverse.milkyway.httpclient.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.response.AttendanceSheetResponse;
import woowacourse.crewniverse.milkyway.response.AttendanceSheetResponses;

public class FakeAttendanceCrawler implements AttendanceCrawler {
    @Override
    public AttendanceSheetResponses execute() {
        String currentDateString = getCurrentDateString();
        return new AttendanceSheetResponses(List.of(
                new AttendanceSheetResponse(currentDateString, "포케", "잠실")
        ));
    }

    private String getCurrentDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. M. d a h:mm:ss", Locale.KOREA);
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}
