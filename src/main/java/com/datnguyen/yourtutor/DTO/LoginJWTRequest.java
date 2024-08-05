package com.datnguyen.yourtutor.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginJWTRequest {
    String username;
    String password;
}
