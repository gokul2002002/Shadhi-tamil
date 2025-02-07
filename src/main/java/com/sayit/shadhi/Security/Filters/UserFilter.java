package com.sayit.shadhi.Security.Filters;

import com.sayit.shadhi.Security.Authentication.SecurityAuthentication;
import com.sayit.shadhi.Security.SecurityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserFilter extends OncePerRequestFilter {

    @Autowired
    private  SecurityManager securityManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String headerKey = request.getHeader("key");
        if(headerKey.isEmpty()){
            response.setStatus(HttpStatus.BAD_GATEWAY.value());
            response.getWriter().write("Missing jwt token");
            return;
        }
        SecurityAuthentication auth = (SecurityAuthentication) securityManager.authenticate(new SecurityAuthentication(false , headerKey));
        if(auth.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request , response);
        }
        else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getWriter().write("Jwt is not valid");
            return;
        }
    }
}
