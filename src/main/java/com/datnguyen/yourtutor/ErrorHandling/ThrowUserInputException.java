package com.datnguyen.yourtutor.ErrorHandling;

import com.datnguyen.yourtutor.DTO.SignUpJWTRequest;
import com.datnguyen.yourtutor.Service.UserInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThrowUserInputException {
    private final UserInputService userInputService;
    public void throwUserInput (SignUpJWTRequest signUpJWTRequest){
        // Sign up information
        if(!userInputService.isValidEmailAddress(signUpJWTRequest.getEmail())){
            throw new RuntimeException("Your email is not valid");
        }
        if(!userInputService.isValidAgeForTutor(signUpJWTRequest.getDob()) && signUpJWTRequest.getRole().equals("TUTOR")){
            throw new RuntimeException("You are not old enough to be a tutor");
        }
        if(!userInputService.isValidAgeForStudent(signUpJWTRequest.getDob()) && signUpJWTRequest.getRole().equals("STUDENT")){
            throw new RuntimeException("You are not old enough to be a student");
        }
        // Check if username already exists
        if(userInputService.isEmailExisted(signUpJWTRequest.getEmail())){
            throw new RuntimeException("This user is already exists");
        }
        // Check if passwords match
        if (!userInputService.matchedPassword(signUpJWTRequest.getPassword(), signUpJWTRequest.getRetypePassword())) {
            throw new RuntimeException("Passwords do not match");
        }

    }
}
