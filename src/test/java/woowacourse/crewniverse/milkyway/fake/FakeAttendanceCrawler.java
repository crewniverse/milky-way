package woowacourse.crewniverse.milkyway.fake;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import woowacourse.crewniverse.milkyway.service.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponse;

public class FakeAttendanceCrawler implements AttendanceCrawler {
    @Override
    public List<AttendanceSheetResponse> execute() {
        String currentDateString = getCurrentDateString();
        return List.of(
                new AttendanceSheetResponse(currentDateString, "포케", "잠실")
        );
    }

    private String getCurrentDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. M. d a h:mm:ss", Locale.KOREA);
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}
