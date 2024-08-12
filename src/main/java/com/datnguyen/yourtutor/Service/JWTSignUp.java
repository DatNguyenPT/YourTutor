package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.DTO.SignUpJWTRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTSignUp implements SignUpMethod{
    private final AuthService authService;
    @Autowired
    public JWTSignUp(AuthService authService){
        this.authService = authService;
    }

    @Override
    public AuthenticationResponse signUp(SignUpJWTRequest signUpJWTRequest) {
        return authService.registration(signUpJWTRequest);
    }
}
