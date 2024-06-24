package poke.fromitive.attendance.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import poke.fromitive.attendance.fake.FakeAttendanceCrawler;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;

@TestConfiguration
public class TestCrawlerConfiguration {

    @Bean
    @Primary
    AttendanceCrawler testAttendanceCrawler() {
        return new FakeAttendanceCrawler();
    }
}
