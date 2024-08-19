package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.DTO.*;
import com.datnguyen.yourtutor.ErrorHandling.ThrowUserInputException;
import com.datnguyen.yourtutor.Repository.UserRepo;
import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.Security.JWTService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserManagementService userManagementService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ThrowUserInputException throwUserInputException;


    public AuthenticationResponse registration(SignUpJWTRequest signUpJWTRequest){
        // Throw input exception
        throwUserInputException.throwUserInput(signUpJWTRequest);

        String userID = "";
        String currentTutorSize = String.valueOf(userManagementService.getAllTutors().size() + 1);
        String currentStudentSize = String.valueOf(userManagementService.getAllStudents().size() + 1);

        Role role = null;

        if(signUpJWTRequest.getRole().equals("STUDENT")){
            userID = "S" + currentStudentSize;
            role = Role.STUDENT;
        }else if (signUpJWTRequest.getRole().equals("TUTOR")){
            userID = "T" + currentTutorSize;
            role = Role.TUTOR;
        }
        System.out.println("Generated User ID: " + userID);
        // Proceed with registration
        UserManagement user = UserManagement.builder()
                .email(signUpJWTRequest.getEmail())
                .pass(passwordEncoder.encode(signUpJWTRequest.getPassword()))
                .id(userID)
                .role(role)
                .dob(signUpJWTRequest.getDob())
                .joinedDate(LocalDate.now())
                .phoneNumber(signUpJWTRequest.getPhoneNum())
                .name(signUpJWTRequest.getName())
                .build();
        System.out.println("User object: " + user);
        userRepo.save(user);
        String jwtToken = jwtService.tokenGenerator(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(LoginJWTRequest loginJWTRequest){
        UserManagement user = userManagementService.getUserByEmail(loginJWTRequest.getEmail());
        String jwtToken = "Undefined";
        try{
            if(user.getRole().name().equals(loginJWTRequest.getRole())){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginJWTRequest.getEmail(),
                                loginJWTRequest.getPassword()
                        )
                );
                jwtToken = jwtService.tokenGenerator(user);

            }else{
                jwtToken = "Undefined";
            }
        }catch (IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
        }
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
