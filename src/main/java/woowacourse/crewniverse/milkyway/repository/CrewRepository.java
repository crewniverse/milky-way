package woowacourse.crewniverse.milkyway.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import woowacourse.crewniverse.milkyway.domain.Crew;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    @Query("SELECT c FROM Crew c LEFT JOIN Attendance a ON c = a.crew AND a.date = :date WHERE a.id IS NULL")
    List<Crew> findByAbsentedCrew(LocalDate date);
}
