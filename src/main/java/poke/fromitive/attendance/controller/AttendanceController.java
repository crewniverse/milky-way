package poke.fromitive.attendance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import poke.fromitive.attendance.response.AttendanceResponse;
import poke.fromitive.attendance.service.AttendanceService;

import java.util.List;

@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(final AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/crews")
    ResponseEntity<List<AttendanceResponse>> getAttendance() {
        List<AttendanceResponse> attendanceResponses = attendanceService.getNotAttendanceCrew();
        return ResponseEntity.ok(attendanceResponses);
    }
}

