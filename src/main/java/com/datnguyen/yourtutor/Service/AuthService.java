package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.DTO.*;
import com.datnguyen.yourtutor.Repository.UserRepo;
import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.Security.JWTService;
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
    public AuthenticationResponse registration(SignUpJWTRequest signUpJWTRequest){
        // Check if username already exists
        if(userManagementService.getUserByEmail(signUpJWTRequest.getEmail()) != null){
            throw new RuntimeException("Username already exists");
        }
        // Check if passwords match
        if (!signUpJWTRequest.getPassword().equals(signUpJWTRequest.getRetypePassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        String userID = "";
        String currentSize = String.valueOf(userManagementService.getAllUsers().size() + 1);
        Role role = null;

        if(signUpJWTRequest.getRole().equals("STUDENT")){
            userID = "S" + currentSize;
            role = Role.STUDENT;
        }else if (signUpJWTRequest.getRole().equals("TUTOR")){
            userID = "T" + currentSize;
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginJWTRequest.getEmail(),
                        loginJWTRequest.getPassword()
                )
        );
        String jwtToken = jwtService.tokenGenerator(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
