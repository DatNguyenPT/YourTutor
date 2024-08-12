package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.DTO.LoginJWTRequest;

public interface LoginMethods {
    AuthenticationResponse login(LoginJWTRequest loginJWTRequest);
}
