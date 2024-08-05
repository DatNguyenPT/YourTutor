package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.DTO.Role;
import com.datnguyen.yourtutor.DTO.UserManagement;
import com.datnguyen.yourtutor.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManagementService{
    private final UserRepo userRepo;
    @Autowired
    public UserManagementService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    // Need to implement pagination
    public List<UserManagement> getAllTutorInfo() {
        return userRepo.findAll().stream()
                .filter(um -> um.getRole().equals(Role.tutor))
                .collect(Collectors.toList());
    }

    public UserManagement getTutorById(int id){
        List<UserManagement>list = getAllTutorInfo();
        return list.stream()
                .filter(tutor -> tutor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public UserManagement getTutorByUsername(String name){
        List<UserManagement>list = getAllTutorInfo();
        return list.stream()
                .filter(tutor -> tutor.getUsername().equals(name))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
