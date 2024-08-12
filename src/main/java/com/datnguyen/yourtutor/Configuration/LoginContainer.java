package com.datnguyen.yourtutor.Configuration;

import com.datnguyen.yourtutor.Service.AuthService;
import com.datnguyen.yourtutor.Service.JWTLogin;
import com.datnguyen.yourtutor.Service.LoginMethods;
import com.datnguyen.yourtutor.Service.OauthLogin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginContainer {
    /*@Bean
    @Qualifier("oauth2")
    public LoginMethods Oauth(){
        return new OauthLogin();
    }*/
    @Bean
    @Qualifier("jwt")
    public LoginMethods JWT(AuthService authService){
        return new JWTLogin(authService);
    }
}
