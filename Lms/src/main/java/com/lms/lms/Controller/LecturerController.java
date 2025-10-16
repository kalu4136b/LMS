package com.lms.lms.Controller;


import com.lms.lms.DTO.LecturerDTO;
import com.lms.lms.DTO.ResponseDTO;
import com.lms.lms.Service.LecturerService;
import com.lms.lms.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/Lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/savelecturer")
    public ResponseEntity saveLecturer(@RequestBody LecturerDTO lecturerDTO) {
        try{
            String res = lecturerService.saveLecturer(lecturerDTO);


            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(lecturerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")){
                responseDTO.setCode(VarList.Duplicated);
                responseDTO.setMsg("Administer alredy Registed");
                responseDTO.setContent(lecturerDTO);
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

    @PutMapping("/updatelecturer")
    public ResponseEntity updateLecturer(@RequestBody LecturerDTO lecturerDTO) {
        try{
            String res = lecturerService.updateLecturer(lecturerDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(lecturerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")){
                responseDTO.setCode(VarList.Duplicated);
                responseDTO.setMsg("Not a Registed Admin");
                responseDTO.setContent(lecturerDTO);
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

    @GetMapping("/getAlllecturers")
    public ResponseEntity getAllLecturers() {
        try{
            List<LecturerDTO> lecturerDTOList = lecturerService.getAllLecturer();
            responseDTO.setCode(VarList.Success);
            responseDTO.setMsg("success");
            responseDTO.setContent(lecturerDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception e){
            responseDTO.setCode(VarList.Fail);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchlecturer/{id}")
    public ResponseEntity searchAdmin(@PathVariable Long id) {
        try{
            LecturerDTO lecturerDTO = lecturerService.searchLecturer(id);
            if(lecturerDTO != null){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(lecturerDTO);
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

    @DeleteMapping("/deletelecturer/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {
        try{
            String res = lecturerService.deleteLecturer(id);
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
