package com.datnguyen.yourtutor.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpJWTRequest {
    private String email;
    private String password;
    private String retypePassword;
    private String phoneNum;
    private String name;
    private LocalDate dob;
    private String role;
}
