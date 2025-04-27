package com.example.attendanceservice.service;

import com.example.attendanceservice.model.Attendance;
import com.example.attendanceservice.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public List<Attendance> getAllAttendances() {
        log.info("Fetching all attendance records");
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendancesByEmployeeId(Long employeeId) {
        log.info("Fetching attendance records for employee: {}", employeeId);
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public List<Attendance> getAttendancesByDate(LocalDate date) {
        log.info("Fetching attendance records for date: {}", date);
        return attendanceRepository.findByDate(date);
    }

    public List<Attendance> getAttendancesByEmployeeIdAndDate(Long employeeId, LocalDate date) {
        log.info("Fetching attendance records for employee: {} on date: {}", employeeId, date);
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
    }

    public Optional<Attendance> getAttendanceById(Long id) {
        log.info("Fetching attendance record with id: {}", id);
        return attendanceRepository.findById(id);
    }

    public Attendance saveAttendance(Attendance attendance) {
        log.info("Saving new attendance record: {}", attendance);
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        log.info("Deleting attendance record with id: {}", id);
        attendanceRepository.deleteById(id);
    }
}