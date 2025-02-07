package com.sayit.shadhi.Security.Providers;

import com.sayit.shadhi.Security.Authentication.SecurityAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SecurityProvider implements AuthenticationProvider {

    @Value("${security.secret.key}")
    private String securityKey;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SecurityAuthentication securityAuthentication = (SecurityAuthentication) authentication;
        if (securityAuthentication.getKey().equals(securityKey)){

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
