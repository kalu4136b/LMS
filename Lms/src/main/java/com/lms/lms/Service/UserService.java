package com.lms.lms.Service;

import com.lms.lms.DTO.AdminDTO;
import com.lms.lms.DTO.StudentDTO;
import com.lms.lms.DTO.UserDTO;
import com.lms.lms.Model.Users;
import com.lms.lms.Repos.UserRepo;
import com.lms.lms.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

//Student services
   public String saveStudent(StudentDTO studentDTO) {
       if(userRepo.existsById(studentDTO.getId())) {
           return VarList.Duplicated;

       }else{
           userRepo.save(modelMapper.map(studentDTO,Users.class));
           return VarList.Success;
       }

   }


   public String updateStudent(StudentDTO studentDTO) {
       if(userRepo.existsById(studentDTO.getId())) {
            userRepo.save(modelMapper.map(studentDTO,Users.class));
            return VarList.Success;
       }else{
            return VarList.NO_Data_Found;
       }
   }

   public List<StudentDTO> getAllStudents() {
       List<Users> usersList = userRepo.findAll();
       return modelMapper.map(usersList,new TypeToken<ArrayList<UserDTO>>(){}.getType());
   }

   public StudentDTO searchStudent(Long id) {
       if(userRepo.existsById(id)) {
           Users users = userRepo.findById(id).orElse(null);
           return modelMapper.map(users,StudentDTO.class);
       }else{
            return null;
       }
   }

   public String deleteStudent(Long id) {
       if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return VarList.Success;
       }else{
           return VarList.NO_Data_Found;
       }
   }



}
