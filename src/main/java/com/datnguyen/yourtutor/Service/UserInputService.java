package com.datnguyen.yourtutor.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class UserInputService {
    private final EmailService emailService;
    private final UserManagementService userManagementService;
    public int calculateAge(LocalDate dob) {
        LocalDate curDate = LocalDate.now();
        if ((dob != null) && (curDate != null)) {
            return Period.between(dob, curDate).getYears();
        } else {
            return 0;
        }
    }
    public boolean isValidAgeForTutor(LocalDate dob){
        int age = calculateAge(dob);
        if(age >= 20)
            return true;
        return false;
    }
    public boolean isValidAgeForStudent(LocalDate dob){
        int age = calculateAge(dob);
        if(age >= 5)
            return true;
        return false;
    }
    public boolean isValidEmailAddress(String email) {
        return emailService.isValidEmailAddress(email);
    }
    public boolean isEmailExisted(String username){
        return userManagementService.getUserByUsername(username) != null;
    }
    public boolean matchedPassword(String password, String retypePassword){
        return password.equals(retypePassword);
    }
}