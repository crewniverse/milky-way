package poke.fromitive.attendance.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import poke.fromitive.attendance.BaseSpringBootTest;

public abstract class AbstractControllerTest extends BaseSpringBootTest {

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}
