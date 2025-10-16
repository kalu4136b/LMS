package com.lms.lms.Repos;

import com.lms.lms.Model.attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;


@Repository
public interface AttendanceRepository extends JpaRepository<attendance, Long> {
    List<attendance> findByDate(LocalDate date);
    List<attendance> findByDateBetween(LocalDate from, LocalDate to);
    List<attendance> findByStudentIdAndDateBetween(String studentId, LocalDate from, LocalDate to);
}