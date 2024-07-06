package woowacourse.crewniverse.milkyway.service.response;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttendanceSheetResponseTest {

    @DisplayName("출석 시간과 이름을 가지고 sheet의 응답값을 생성할 수 있다")
    @Test
    void should_create_AttendanceSheetResponse() {
        String rawName = "포케";
        String rawCampusName = "잠실";
        Assertions.assertDoesNotThrow(() ->
                new AttendanceSheetResponse(LocalDate.of(2024, 7, 2), rawName, rawCampusName)
        );
    }
}
