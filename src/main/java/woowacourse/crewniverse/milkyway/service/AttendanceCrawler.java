package woowacourse.crewniverse.milkyway.service;

import java.time.LocalDate;
import java.util.List;
import woowacourse.crewniverse.milkyway.service.response.AttendanceSheetResponse;

public interface AttendanceCrawler {
    List<AttendanceSheetResponse> getAttendancesDateOf(LocalDate date);
}
