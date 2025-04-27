package com.example.attendanceservice.controller;

import com.example.attendanceservice.model.Attendance;
import com.example.attendanceservice.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
@Slf4j
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendances() {
        log.info("GET request to fetch all attendance records");
        return ResponseEntity.ok(attendanceService.getAllAttendances());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendancesByEmployeeId(@PathVariable Long employeeId) {
        log.info("GET request to fetch attendance records for employee: {}", employeeId);
        return ResponseEntity.ok(attendanceService.getAttendancesByEmployeeId(employeeId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Attendance>> getAttendancesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("GET request to fetch attendance records for date: {}", date);
        return ResponseEntity.ok(attendanceService.getAttendancesByDate(date));
    }

    @GetMapping("/employee/{employeeId}/date/{date}")
    public ResponseEntity<List<Attendance>> getAttendancesByEmployeeIdAndDate(
            @PathVariable Long employeeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("GET request to fetch attendance records for employee: {} on date: {}", employeeId, date);
        return ResponseEntity.ok(attendanceService.getAttendancesByEmployeeIdAndDate(employeeId, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        log.info("GET request to fetch attendance record with id: {}", id);
        return attendanceService.getAttendanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        log.info("POST request to create a new attendance record");
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.saveAttendance(attendance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        log.info("PUT request to update attendance record with id: {}", id);
        return attendanceService.getAttendanceById(id)
                .map(existingAttendance -> {
                    attendance.setId(id);
                    return ResponseEntity.ok(attendanceService.saveAttendance(attendance));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        log.info("DELETE request to remove attendance record with id: {}", id);
        return attendanceService.getAttendanceById(id)
                .map(attendance -> {
                    attendanceService.deleteAttendance(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}