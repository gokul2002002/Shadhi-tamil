package com.sayit.shadhi.Security.Authentication;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ContextImplementation implements ContextAuth{

    private final Authentication authentication;

    public ContextImplementation(){
        this.authentication = ContextAuth.super.authentication();
    }

    @Override
    public Authentication getAuthentication() {
        return this.authentication;
    }

}
