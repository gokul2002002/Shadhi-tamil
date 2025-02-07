package com.sayit.shadhi.Configurations;

import com.auth0.jwt.JWTCreator;
import com.sayit.shadhi.Security.Filters.UserFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {


    private final UserFilter userFilter;

    public SecurityConfiguration(UserFilter userFilter) {
        this.userFilter = userFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SuppressWarnings(value = "all")
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return  httpSecurity
                .addFilterBefore(userFilter , UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((request)->{
                    request
                            .requestMatchers("/auth/**").permitAll()
                            .anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .build();
    }

}
