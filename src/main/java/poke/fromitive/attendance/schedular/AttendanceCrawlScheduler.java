package poke.fromitive.attendance.schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import poke.fromitive.attendance.response.AttendanceSheetResponse;
import poke.fromitive.attendance.service.AttendanceService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AttendanceCrawlScheduler {
    private static final Logger log = LoggerFactory.getLogger(AttendanceSheetResponse.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final AttendanceService attendanceService;

    public AttendanceCrawlScheduler(final AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Scheduled(fixedRate = 3000)
    @Async
    public void crawlAttendnace() throws IOException, GeneralSecurityException {
        log.info("query StartTime {}", dateFormat.format(new Date()));
        attendanceService.updateAttendance();
    }
}
