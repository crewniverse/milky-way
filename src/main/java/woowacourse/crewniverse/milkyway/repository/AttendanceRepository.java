package woowacourse.crewniverse.milkyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woowacourse.crewniverse.milkyway.domain.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
