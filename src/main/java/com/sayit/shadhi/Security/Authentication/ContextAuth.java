package com.sayit.shadhi.Security.Authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

public interface ContextAuth {

    default  Authentication authentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Authentication getAuthentication();
}
