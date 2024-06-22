package poke.fromitive.attendance.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StaticPageControllerTest extends AbstractControllerTest{

    @DisplayName("메인 페이지를 정상적으로 불러온다.")
    @Test
    void should_response_index_page(){
        RestAssured.given().log().all()
                .when().get("/")
                .then().log().all()
                .statusCode(200);
    }
}