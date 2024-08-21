package com.datnguyen.yourtutor.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginJWTRequest {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 8, message = "Username should have at least 6 characters")
    private String username;;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should have at least 6 characters")
    private String password;

    @NotEmpty(message = "Role cannot be empty")
    private String role;
}
