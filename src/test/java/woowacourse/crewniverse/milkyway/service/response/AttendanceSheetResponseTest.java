package woowacourse.crewniverse.milkyway.service.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttendanceSheetResponseTest {

    @DisplayName("출석 시간과 이름을 가지고 sheet의 응답값을 생성할 수 있다")
    @Test
    void should_create_AttendanceSheetResponse() {
        String rawDate = "2024. 5. 1 오전 9:06:00";
        String rawName = "포케";
        String rawCampusName = "잠실";
        new AttendanceSheetResponse(rawDate, rawName, rawCampusName);
    }
}
