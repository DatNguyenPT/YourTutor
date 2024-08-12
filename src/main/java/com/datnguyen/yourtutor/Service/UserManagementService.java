package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.DTO.Role;
import com.datnguyen.yourtutor.DTO.UserManagement;
import com.datnguyen.yourtutor.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementService{
    private final UserRepo userRepo;
    @Autowired
    public UserManagementService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    public List<UserManagement> getAllUserInfoByRole(String role) {
        return userRepo.findAll().stream()
                .filter(um -> um.getRole().equals(role))
                .collect(Collectors.toList());
    }

    public List<UserManagement> getAllUsers(){
        return userRepo.findAll().stream()
                .collect(Collectors.toList());
    }

    public UserManagement getUserById(String id){
        List<UserManagement>list;
        if(id.startsWith("S")){
            list = getAllUserInfoByRole("STUDENT");
        }else if(id.startsWith("T")){
            list = getAllUserInfoByRole("TUTOR");
        }else{
            throw new IllegalArgumentException("INVALID ID");
        }
        return list.stream()
                .filter(tutor -> tutor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public UserManagement getUserByEmail(String email){
        List<UserManagement>list = getAllUsers();
        return list.stream()
                .filter(um -> um.getUsername().equals(email))
                .findFirst()
                .orElse(null);
    }
}
