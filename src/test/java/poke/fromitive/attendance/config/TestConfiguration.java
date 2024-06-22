package poke.fromitive.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poke.fromitive.attendance.fake.FakeAttendanceCrawler;
import poke.fromitive.attendance.httpclient.AttendanceCrawler;

@Configuration
public class TestConfiguration {

    @Bean
    AttendanceCrawler attendanceCrawler() {
        return new FakeAttendanceCrawler();
    }
}
