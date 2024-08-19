package com.datnguyen.yourtutor.Controller;

import com.datnguyen.yourtutor.DTO.LoginJWTRequest;
import com.datnguyen.yourtutor.DTO.SignUpJWTRequest;
import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.Service.JWTLogin;
import com.datnguyen.yourtutor.Service.JWTSignUp;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
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
            String jwtToken = response.getToken();

            ResponseCookie cookie = ResponseCookie.from("jwtToken", jwtToken)
                    .httpOnly(true)
                    .secure(true) // Use secure=true in production (requires HTTPS)
                    .path("/")
                    .maxAge(60 * 60 * 24) // Set token expiration time (in seconds)
                    .sameSite("Strict") // Set SameSite attribute (Strict, Lax, or None)
                    .build();

            return ResponseEntity.ok()
                    .header("Set-Cookie", cookie.toString())
                    .body("Register Successfully");
        } catch (Exception e) {
            logger.error("Registration failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Register Failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginJWTRequest loginJWTRequest) {
        try {
            AuthenticationResponse response = jwtLogin.login(loginJWTRequest);
            String jwtToken = response.getToken();

            if(!jwtToken.equals("Undefined")){
                ResponseCookie cookie = ResponseCookie.from("jwtToken", jwtToken)
                        .httpOnly(true)
                        .secure(true) // Use secure=true in production (requires HTTPS)
                        .path("/")
                        .maxAge(60 * 60 * 24) // Set token expiration time (in seconds)
                        .sameSite("Strict") // Set SameSite attribute (Strict, Lax, or None)
                        .build();
                return ResponseEntity.ok()
                        .header("Set-Cookie", cookie.toString())
                        .body("Login Successful");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login Failed");
            }

        } catch (Exception e) {
            logger.error("Login failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login Failed: " + e.getMessage());
        }
    }
}
