package com.datnguyen.yourtutor.Controller;

import com.datnguyen.yourtutor.Security.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

        //return ResponseEntity.ok("THIS IS A SECURED ENDPOINT");
    }

    @GetMapping("/google/demo")
    public String oauth2GGTest() {
        return "THIS IS A SECURED ENDPOINT";
    }
}
