package com.lms.lms.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "attendance")public class attendance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(name = "student_id", nullable = false)
        private String studentId;


        @Column(name = "date", nullable = false)
        private LocalDate date;


        @Column(name = "time", nullable = false)
        private LocalTime time;


        public attendance() {}


        public attendance(String studentId, LocalDate date, LocalTime time) {
            this.studentId = studentId;
            this.date = date;
            this.time = time;
        }


        public Long getId() { return id; }
        public String getStudentId() { return studentId; }
        public LocalDate getDate() { return date; }
        public LocalTime getTime() { return time; }


        public void setId(Long id) { this.id = id; }
        public void setStudentId(String studentId) { this.studentId = studentId; }
        public void setDate(LocalDate date) { this.date = date; }
        public void setTime(LocalTime time) { this.time = time; }
    }

