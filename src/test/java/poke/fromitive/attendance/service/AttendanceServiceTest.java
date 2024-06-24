package poke.fromitive.attendance.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import poke.fromitive.attendance.BaseSpringBootTest;
import poke.fromitive.attendance.response.AttendanceResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"/test_data.sql"})
class AttendanceServiceTest extends BaseSpringBootTest {

    @Autowired
    private AttendanceService attendanceService;

    @DisplayName("출석부를 업데이트 한다.")
    @Test
    void should_update_attendance() throws GeneralSecurityException, IOException {
        attendanceService.updateAttendance();
        final List<AttendanceResponse> notAttendanceCrew = attendanceService.getNotAttendanceCrew();
        assertThat(notAttendanceCrew).isEmpty();
    }
}