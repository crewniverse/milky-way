package woowacourse.crewniverse.milkyway.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import woowacourse.crewniverse.milkyway.domain.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByLastAttendedDateNotOrLastAttendedDateIsNull(LocalDate date);
}
