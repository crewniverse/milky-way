package woowacourse.crewniverse.milkyway.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Crew crew;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    protected Attendance() {
    }

    private Attendance(final Long id, final Crew crew, final LocalDate date) {
        this.id = id;
        this.crew = crew;
        this.date = date;
    }

    public Attendance(final Crew crew, LocalDate date) {
        this(null, crew, date);
    }

    public Long getId() {
        return id;
    }

    public Crew getCrew() {
        return crew;
    }

    public String getCrewName() {
        return crew.getName();
    }
}
