package com.datnguyen.yourtutor.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testauth")
public class TestController {
    @GetMapping("/demo")
    public ResponseEntity<String>test(){
        return ResponseEntity.ok("This is a secured endpoint");
    }
}
