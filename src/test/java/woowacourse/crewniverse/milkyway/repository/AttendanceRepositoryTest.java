package woowacourse.crewniverse.milkyway.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import woowacourse.crewniverse.milkyway.domain.Attendance;

@DataJpaTest
class AttendanceRepositoryTest {

    @Autowired
    AttendanceRepository attendanceRepository;

    @DisplayName("오늘 출석하지 않은 크루리스트를 반환한다.")
    @Test
    @Sql({"/test_data.sql"})
    void shouldReturnNotAttendedCrews() {
        final List<Attendance> attendances = attendanceRepository.findByLastAttendedDateNotOrLastAttendedDateIsNull(
                LocalDate.now());
        assertThat(attendances).hasSize(1);
    }
}
