package woowacourse.crewniverse.milkyway.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import woowacourse.crewniverse.milkyway.BaseSpringBootTest;
import woowacourse.crewniverse.milkyway.service.response.AttendanceResponse;

@Sql({"/test_data.sql"})
class AttendanceServiceTest extends BaseSpringBootTest {

    @Autowired
    private AttendanceService attendanceService;

    @DisplayName("출석부를 업데이트 한다.")
    @Test
    void should_update_attendance() {
        attendanceService.updateAttendance();
        final List<AttendanceResponse> notAttendanceCrew = attendanceService.getAbsentCrew();
        assertThat(notAttendanceCrew).hasSize(2);
    }
}
