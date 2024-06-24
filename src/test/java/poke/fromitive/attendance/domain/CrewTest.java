package poke.fromitive.attendance.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CrewTest {
    @DisplayName("캠퍼스가 다르면 같지 않다.")
    @Test
    void should_be_not_equal_when_different_campus() {
        Crew crew1 = new Crew("포케", "잠실");
        Crew crew2 = new Crew("포케", "선릉");

        assertThat(crew1).isNotEqualTo(crew2);
    }
}
