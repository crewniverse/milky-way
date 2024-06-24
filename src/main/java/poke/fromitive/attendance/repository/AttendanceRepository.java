package poke.fromitive.attendance.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import poke.fromitive.attendance.domain.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByLastAttendedDateNotOrLastAttendedDateIsNull(LocalDate date);
}
