package woowacourse.crewniverse.milkyway.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "crew")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "campus", nullable = false)
    @Enumerated(EnumType.STRING)
    private Campus campus;

    protected Crew() {
    }

    public Crew(final String name, final Campus campus) {
        this.name = name;
        this.campus = campus;
    }

    public String getName() {
        return name;
    }

    public String getCampusName() {
        return campus.getName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Crew crew = (Crew) o;
        return Objects.equals(name, crew.name) && campus == crew.campus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, campus);
    }
}
