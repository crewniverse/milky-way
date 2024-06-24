package poke.fromitive.attendance.httpclient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import poke.fromitive.attendance.response.AttendanceSheetResponses;

public interface AttendanceCrawler {
    AttendanceSheetResponses execute() throws GeneralSecurityException, IOException;
}
