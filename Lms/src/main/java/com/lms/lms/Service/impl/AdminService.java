package com.lms.lms.Service.impl;

import com.lms.lms.DTO.AdminDTO;
import com.lms.lms.DTO.UserDTO;
import com.lms.lms.Model.Users;
import com.lms.lms.Repos.UserRepo;
import com.lms.lms.Service.AdminaService;
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
public class AdminService implements AdminaService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveAdmin(AdminDTO adminDTO) {
        if(userRepo.existsById(adminDTO.getId())) {
            return VarList.Duplicated;
        }else{
            userRepo.save(modelMapper.map(adminDTO, Users.class));
            return VarList.Success;
        }

    }

    public String updateAdmin(AdminDTO adminDTO) {
        if(userRepo.existsById(adminDTO.getId())) {
            userRepo.save(modelMapper.map(adminDTO,Users.class));
            return VarList.Success;
        }else{
            return VarList.NO_Data_Found;
        }
    }

    public List<AdminDTO> getAllAdmins() {
        List<Users> usersList = userRepo.findAll();
        return modelMapper.map(usersList,new TypeToken<ArrayList<UserDTO>>(){}.getType());
    }

    public AdminDTO searchAdmin(Long id) {
        if(userRepo.existsById(id)) {
            Users users = userRepo.findById(id).orElse(null);
            return modelMapper.map(users,AdminDTO.class);
        }else{
            return null;
        }
    }

    public String deleteAdmin(Long id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return VarList.Success;
        }else{
            return VarList.NO_Data_Found;
        }
    }
}
