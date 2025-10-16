package com.lms.lms.DTO;

public class StudentDTO extends UserDTO {

    private int StudentId;
    private int CourseId;

    public StudentDTO() {
        super.setRole("Student");
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }
}
