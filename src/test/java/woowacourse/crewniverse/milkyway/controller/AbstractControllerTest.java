package woowacourse.crewniverse.milkyway.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import woowacourse.crewniverse.milkyway.BaseSpringBootTest;

public abstract class AbstractControllerTest extends BaseSpringBootTest {

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}
