package com.sayit.shadhi.Security;

import com.sayit.shadhi.Security.Providers.SecurityProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SecurityManager implements AuthenticationManager {

    @Autowired
    private  SecurityProvider securityProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(securityProvider.supports(authentication.getClass())){
            return securityProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Oh no bad credential!");
    }
}
