package woowacourse.crewniverse.milkyway.fake;

import java.time.LocalDate;
import java.util.List;
import woowacourse.crewniverse.milkyway.service.AttendanceCrawler;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponse;

public class FakeAttendanceCrawler implements AttendanceCrawler {
    @Override
    public List<AttendanceSheetResponse> getAttendancesDateOf(LocalDate date) {
        return List.of(
                new AttendanceSheetResponse(date, "포케", "잠실")
        );
    }
}
