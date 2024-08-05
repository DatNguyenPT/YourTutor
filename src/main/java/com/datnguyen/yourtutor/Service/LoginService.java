package com.datnguyen.yourtutor.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginService{
    private final LoginMethods loginMethods;
    @Autowired
    public LoginService(@Qualifier("jwt") LoginMethods loginMethods){
        this.loginMethods = loginMethods;
    }
}
