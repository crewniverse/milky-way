package woowacourse.crewniverse.milkyway.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import woowacourse.crewniverse.milkyway.domain.Crew;

@DataJpaTest
@Sql({"/test_data.sql"})
class CrewRepositoryTest {

    @Autowired
    CrewRepository crewRepository;

    @DisplayName("오늘 출석하지 않은 크루리스트를 반환한다.")
    @Test
    void shouldReturnAbsentedCrews() {
        final List<Crew> crews = crewRepository.findByAbsentedCrew(LocalDate.now());
        assertThat(crews).hasSize(3);
    }
}