package com.lms.lms.Controller;

import com.lms.lms.DTO.ResponseDTO;
import com.lms.lms.DTO.StudentDTO;
import com.lms.lms.Service.UserService;
import com.lms.lms.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/savestudent")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO) {
        try{
            String res = userService.saveStudent(studentDTO);
            studentDTO.setRole("student");
            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")){
                responseDTO.setCode(VarList.Duplicated);
                responseDTO.setMsg("Empoloyee alredy Registed");
                responseDTO.setContent(studentDTO);
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

    @PutMapping("/updatestudent")
    public ResponseEntity updateStudent(@RequestBody StudentDTO studentDTO) {
        try{
            String res = userService.updateStudent(studentDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.Success);
                responseDTO.setMsg("success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")){
                responseDTO.setCode(VarList.Duplicated);
                responseDTO.setMsg("Not a Registed Student");
                responseDTO.setContent(studentDTO);
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

    @GetMapping("/getAllStudents")
    public ResponseEntity getAllStudents() {
        try{
            List<StudentDTO> studentDTOList = userService.getAllStudents();
            responseDTO.setCode(VarList.Success);
            responseDTO.setMsg("success");
            responseDTO.setContent(studentDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception e){
            responseDTO.setCode(VarList.Fail);
            responseDTO.setMsg(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchstudent/{id}")
    public ResponseEntity searchStudent(@PathVariable Long id) {
            try{
                StudentDTO studentDTO = userService.searchStudent(id);
                if(studentDTO != null){
                    responseDTO.setCode(VarList.Success);
                    responseDTO.setMsg("success");
                    responseDTO.setContent(studentDTO);
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

    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        try{
           String res = userService.deleteStudent(id);
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
