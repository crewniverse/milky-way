package woowacourse.crewniverse.milkyway.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import woowacourse.crewniverse.milkyway.fake.FakeAttendanceCrawler;
import woowacourse.crewniverse.milkyway.service.AttendanceCrawler;

@TestConfiguration
public class TestCrawlerConfiguration {

    @Bean
    @Primary
    AttendanceCrawler attendanceCrawler() {
        return new FakeAttendanceCrawler();
    }
}
