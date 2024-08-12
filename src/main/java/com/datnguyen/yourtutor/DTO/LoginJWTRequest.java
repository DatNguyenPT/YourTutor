package com.datnguyen.yourtutor.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginJWTRequest {
    private String email;
    private String password;
    private String role;
}
