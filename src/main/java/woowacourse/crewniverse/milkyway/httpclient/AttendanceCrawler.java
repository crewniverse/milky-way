package woowacourse.crewniverse.milkyway.httpclient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import woowacourse.crewniverse.milkyway.response.AttendanceSheetResponses;

public interface AttendanceCrawler {
    AttendanceSheetResponses execute() throws GeneralSecurityException, IOException;
}
