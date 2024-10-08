package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.DTO.LoginJWTRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTLogin implements LoginMethods{
    private final AuthService authService;
    @Autowired
    public JWTLogin(AuthService authService){
        this.authService = authService;
    }
    @Override
    public AuthenticationResponse login(LoginJWTRequest loginJWTRequest) {
        return authService.authenticate(loginJWTRequest);
    }
}
