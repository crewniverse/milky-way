package woowacourse.crewniverse.milkyway.schedular;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import woowacourse.crewniverse.milkyway.service.AttendanceService;

@Component
public class AttendanceCrawlScheduler {
    private static final Logger log = LoggerFactory.getLogger(AttendanceCrawlScheduler.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final AttendanceService attendanceService;

    public AttendanceCrawlScheduler(final AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Scheduled(fixedRate = 3000)
    @Async
    public void crawlAttendance() throws IOException, GeneralSecurityException {
        LocalDateTime now = LocalDateTime.now();
        log.info("query StartTime {}", DATE_TIME_FORMATTER.format(now));
        attendanceService.updateAttendance();
    }
}
