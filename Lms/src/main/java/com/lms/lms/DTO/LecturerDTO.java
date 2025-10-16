package com.lms.lms.DTO;

public class LecturerDTO extends UserDTO {
    private int lecturerId;
    private String FacultyID;

    public LecturerDTO() {
        super.setRole("lecturer");
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getFacultyID() {
        return FacultyID;
    }

    public void setFacultyID(String facultyID) {
        FacultyID = facultyID;
    }
}
