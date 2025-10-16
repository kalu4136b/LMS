package com.lms.lms.Controller;

import com.lms.lms.Model.attendance;
import com.lms.lms.Repos.AttendanceRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {


    private final AttendanceRepository repo;


    public AttendanceController(AttendanceRepository repo) {
        this.repo = repo;
    }


    // Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }


    // Today’s attendance
    @GetMapping("/today")
    public List<attendance> getToday() {
        return repo.findByDate(LocalDate.now());
    }


    // Range query: /api/attendance/range?from=2025-08-28&to=2025-08-31
    @GetMapping("/range")
    public List<attendance> getRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return repo.findByDateBetween(from, to);
    }


    // Student in range: /api/attendance/student/{id}?from=2025-08-01&to=2025-08-31
    @GetMapping("/student/{id}")
    public List<attendance> getStudentRange(
            @PathVariable("id") String studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return repo.findByStudentIdAndDateBetween(studentId, from, to);
    }


    // Optional: allow manual insert (e.g., admin fix)
    @PostMapping
    public attendance add(@RequestBody attendance a) {
        return repo.save(a);
    }
}

