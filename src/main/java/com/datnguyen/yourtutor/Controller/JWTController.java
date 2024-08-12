package com.datnguyen.yourtutor.Controller;

import com.datnguyen.yourtutor.DTO.LoginJWTRequest;
import com.datnguyen.yourtutor.DTO.SignUpJWTRequest;
import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.Service.JWTLogin;
import com.datnguyen.yourtutor.Service.JWTSignUp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class JWTController {

    private static final Logger logger = LoggerFactory.getLogger(JWTController.class);

    private final JWTLogin jwtLogin;
    private final JWTSignUp jwtSignUp;

    @Autowired
    public JWTController(JWTLogin jwtLogin, JWTSignUp jwtSignUp) {
        this.jwtLogin = jwtLogin;
        this.jwtSignUp = jwtSignUp;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> signUp(@RequestBody SignUpJWTRequest signUpJWTRequest) {
        try {
            AuthenticationResponse response = jwtSignUp.signUp(signUpJWTRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Registration failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginJWTRequest loginJWTRequest) {
        try {
            AuthenticationResponse response = jwtLogin.login(loginJWTRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Login failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}