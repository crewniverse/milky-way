package poke.fromitive.attendance.fake;

import poke.fromitive.attendance.httpclient.AttendanceCrawler;
import poke.fromitive.attendance.response.AttendanceSheetResponse;
import poke.fromitive.attendance.response.AttendanceSheetResponses;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class FakeAttendanceCrawler implements AttendanceCrawler {
    @Override
    public AttendanceSheetResponses execute() throws GeneralSecurityException, IOException {
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
