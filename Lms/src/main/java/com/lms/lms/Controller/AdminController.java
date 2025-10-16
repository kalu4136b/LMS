package com.lms.lms.Controller;

import com.lms.lms.DTO.AdminDTO;
import com.lms.lms.DTO.ResponseDTO;
import com.lms.lms.Service.impl.AdminService;
import com.lms.lms.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveadmin")
    public ResponseEntity saveAdmin(@RequestBody AdminDTO adminDTO) {
        try{
            String res = adminService.saveAdmin(adminDTO);


            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")){
                responseDTO.setCode(VarList.Duplicated);
                responseDTO.setMsg("Administer alredy Registed");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.Fail);
                responseDTO.setMsg("Erorr");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }


        }catch(Exception e){
            responseDTO.setCode(VarList.Fail);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateadmin")
    public ResponseEntity updateAdmin(@RequestBody AdminDTO adminDTO) {
        try{
            String res = adminService.updateAdmin(adminDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")){
                responseDTO.setCode(VarList.Duplicated);
                responseDTO.setMsg("Not a Registed Admin");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.Fail);
                responseDTO.setMsg("Erorr");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }


        }catch(Exception e){
            responseDTO.setCode(VarList.Fail);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity getAllAdmin() {
        try{
            List<AdminDTO> adminDTOList = adminService.getAllAdmins();
            responseDTO.setCode(VarList.Success);
            responseDTO.setMsg("success");
            responseDTO.setContent(adminDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception e){
            responseDTO.setCode(VarList.Fail);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchadmin/{id}")
    public ResponseEntity searchAdmin(@PathVariable Long id) {
        try{
            AdminDTO adminDTO = adminService.searchAdmin(id);
            if(adminDTO != null){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(adminDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.NO_Data_Found);
                responseDTO.setMsg("Admin not Registered");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.Error);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteadmin/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {
        try{
            String res = adminService.deleteAdmin(id);
            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.NO_Data_Found);
                responseDTO.setMsg("User not Registered");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.Error);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
