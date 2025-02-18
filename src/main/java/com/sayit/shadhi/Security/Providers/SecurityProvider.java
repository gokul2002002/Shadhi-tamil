package com.sayit.shadhi.Security.Providers;

import com.sayit.shadhi.Exceptions.TokenExpiredException;
import com.sayit.shadhi.Security.Authentication.SecurityAuthentication;
import com.sayit.shadhi.Security.Pojo.UserPOJO;
import com.sayit.shadhi.Security.Service.SecurityDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityProvider implements AuthenticationProvider {


    private final SecurityDecoder securityDecoder;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException , TokenExpiredException {
        SecurityAuthentication securityAuthentication = (SecurityAuthentication) authentication;
        UserPOJO userPOJO =  securityDecoder.getUserFromJwt(securityAuthentication.getToken());
        if (userPOJO.getValid()){
            securityAuthentication.setUserDetails(userPOJO.getUserDetails());
            securityAuthentication.setAuthenticated(true);
            return authentication;
        }
        throw new BadCredentialsException("JWT Token is not valid");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(SecurityAuthentication.class);
    }
}
