package com.datnguyen.yourtutor.Service;

import com.datnguyen.yourtutor.Security.AuthenticationResponse;
import com.datnguyen.yourtutor.DTO.SignUpJWTRequest;

public interface SignUpMethod {
    AuthenticationResponse signUp(SignUpJWTRequest signUpJWTRequest);
}
