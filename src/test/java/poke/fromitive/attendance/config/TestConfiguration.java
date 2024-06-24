package poke.fromitive.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import poke.fromitive.attendance.fake.FakeAttendanceCrawler;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    AttendanceCrawler testAttendanceCrawler() {
        return new FakeAttendanceCrawler();
    }
}
