package com.sayit.shadhi.Security.Authentication;

import com.sayit.shadhi.Security.UserDetails.UserDetailImp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityAuthentication implements Authentication {

    private boolean isAuthenticated;

    private UserDetails userDetails;

    private String token;


    public SecurityAuthentication(boolean isAuthenticated , UserDetails user , String token){
        this.isAuthenticated = isAuthenticated;
        this.userDetails =  user;
        this.token =  token;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails.getUsername();
    }

    @Override
    public Object getPrincipal() {
        return userDetails.toString();
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }
}
