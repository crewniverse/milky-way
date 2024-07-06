package woowacourse.crewniverse.milkyway.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import woowacourse.crewniverse.milkyway.service.AttendanceService;
import woowacourse.crewniverse.milkyway.service.response.AttendanceResponse;

@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(final AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/crews")
    ResponseEntity<List<AttendanceResponse>> getAttendance() {
        List<AttendanceResponse> attendanceResponses = attendanceService.getAbsentCrew();
        return ResponseEntity.ok(attendanceResponses);
    }
}
