package woowacourse.crewniverse.milkyway.service;

import java.util.List;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponse;

public interface AttendanceCrawler {
    List<AttendanceSheetResponse> execute();
}
