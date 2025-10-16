package com.lms.lms.Service;

import com.lms.lms.Model.attendance;
import com.lms.lms.Repos.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<attendance> getTodayAttendance() {
        return attendanceRepository.findByDate(LocalDate.now());
    }

    public List<attendance> getAttendanceBetween(LocalDate from, LocalDate to) {
        return attendanceRepository.findByDateBetween(from, to);
    }

    public List<attendance> getAttendanceForStudent(String studentId, LocalDate from, LocalDate to) {
        return attendanceRepository.findByStudentIdAndDateBetween(studentId, from, to);
    }

    public attendance saveAttendance(attendance attendance) {
        return attendanceRepository.save(attendance);
    }
}
