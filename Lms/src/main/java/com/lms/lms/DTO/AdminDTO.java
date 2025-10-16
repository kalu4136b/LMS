package com.lms.lms.DTO;

import org.springframework.context.annotation.Import;


public class AdminDTO extends UserDTO  {

    private Long adminId;

    private Long adminType;




    public AdminDTO() {
        super.setRole("admin");
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminType() {
        return adminType;
    }

    public void setAdminType(Long adminType) {
        this.adminType = adminType;
    }
}
