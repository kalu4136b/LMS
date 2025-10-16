package com.lms.lms.Repos;

import com.lms.lms.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {


    Optional<Users> findById(Long id);

}
