package poke.fromitive.attendance.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttendanceControllerTest extends AbstractControllerTest {

    @DisplayName("출석하지 않은 크루 이름들을 반환하고 200을 반환한다.")
    @Test
    void should_return_crew_name() {
        RestAssured.given().log().all()
                .when().get("/crews")
                .then().log().all()
                .statusCode(200);
    }
}