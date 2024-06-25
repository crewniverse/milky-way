package woowacourse.crewniverse.milkyway.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Crew crew;

    @Column(name = "last_attended_date", nullable = true)
    private LocalDate lastAttendedDate;

    protected Attendance() {
    }

    public Attendance(final Long id, final Crew crew, final LocalDate lastAttendedDate) {
        this.id = id;
        this.crew = crew;
        this.lastAttendedDate = lastAttendedDate;
    }

    public Long getId() {
        return id;
    }

    public Crew getCrew() {
        return crew;
    }

    public LocalDate getLastAttendedDate() {
        return lastAttendedDate;
    }

    public boolean isNotAttended() {
        return lastAttendedDate == null || LocalDate.now().isEqual(lastAttendedDate);
    }

    public void updateAttendedDate() {
        lastAttendedDate = LocalDate.now();
    }
}
