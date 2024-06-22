package poke.fromitive.attendance.httpclient;

import poke.fromitive.attendance.response.AttendanceSheetResponses;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AttendanceCrawler {
    AttendanceSheetResponses execute() throws GeneralSecurityException, IOException;
}
