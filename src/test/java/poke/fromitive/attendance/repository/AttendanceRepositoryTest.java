package poke.fromitive.attendance.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import poke.fromitive.attendance.domain.Attendance;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AttendanceRepositoryTest {

    @Autowired
    AttendanceRepository attendanceRepository;

    @DisplayName("오늘 출석하지 않은 크루리스트를 반환한다.")
    @Test
    @Sql({"/test_data.sql"})
    void should_return_attendence_not_attended() {
        final List<Attendance> attendances = attendanceRepository.findByLastAttendedDateNotOrLastAttendedDateIsNull(LocalDate.now());
        assertThat(attendances).hasSize(1);
    }
}