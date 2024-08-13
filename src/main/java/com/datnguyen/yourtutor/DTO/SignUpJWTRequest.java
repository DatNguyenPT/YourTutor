package com.datnguyen.yourtutor.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpJWTRequest {
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should have at least 6 characters")
    private String password;

    @NotEmpty(message = "Retype Password cannot be empty")
    @Size(min = 8, message = "Retype Password should have at least 6 characters")
    private String retypePassword;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    private String phoneNum;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Date of birth cannot be empty")
    private LocalDate dob;

    @NotEmpty(message = "Role cannot be empty")
    @Size(min = 1, message = "Role cannot be empty")
    private String role;
}