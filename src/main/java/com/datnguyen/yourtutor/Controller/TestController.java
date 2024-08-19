package com.datnguyen.yourtutor.Controller;

import com.datnguyen.yourtutor.Security.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/test-auth")
public class TestController {
    private final JWTService jwtService;

    public TestController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> test(HttpServletRequest request) {
        // Extract JWT token from HttpOnly cookie
        String jwtToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "jwtToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        if (jwtToken == null) {
            return new ResponseEntity<>("Unauthorized - No JWT token found", HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (!jwtService.isTokenValidate(jwtToken, userDetails) || jwtService.isExpiredToken(jwtToken)) {
            return new ResponseEntity<>("Unauthorized - Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }

        String username = jwtService.extractUserName(jwtToken);
        return ResponseEntity.ok("This is a secured endpoint. Welcome, " + username + "!");
    }
}
