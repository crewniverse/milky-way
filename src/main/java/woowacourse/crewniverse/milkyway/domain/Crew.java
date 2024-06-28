package woowacourse.crewniverse.milkyway.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "campus_name", nullable = false)
    private String campusName;

    protected Crew() {
    }

    public Crew(final String name, final String campusName) {
        this.name = name;
        this.campusName = campusName;
    }

    public String getName() {
        return name;
    }

    public String getCampusName() {
        return campusName;
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
        return Objects.equals(name, crew.name) && Objects.equals(campusName, crew.campusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, campusName);
    }
}
