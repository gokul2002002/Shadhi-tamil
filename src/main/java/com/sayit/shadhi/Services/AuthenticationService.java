package com.sayit.shadhi.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayit.shadhi.DTOs.LoginDTO;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("{security.secret.key}")
    private String secreatKey;

    long expirationTime = 14 * 24 * 60 * 60 * 1000;
    private final UserRepository userRepository;

    private  final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final ObjectMapper objectMapper;


    public ResponseEntity<List<User>> sendOtpForVerification(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public ResponseEntity<String> createUserInDb(User user){
        userRepository.save(user);
        return ResponseEntity.ok("Log in sucess");
    }

    public ResponseEntity<String> isUserisValid(LoginDTO loginDTO) throws UsernameNotFoundException, JsonProcessingException {
        Optional<User> user =  userRepository.findByUserName(loginDTO.getUserName());
        if(user.isPresent()){
            boolean isValid =  passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword() );
            redisService.addItem(user.get().getUserName() , "12324");
            if (isValid){
                String jwt = JWT.create()
                        .withIssuer("shadhi.tamil.com")
                        .withSubject(objectMapper.writeValueAsString(user.get()))
                        .withIssuedAt(new Date())
                        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                        .withClaim("role", "admin") // Add custom claims
                        .sign(Algorithm.HMAC256(secreatKey));
                return ResponseEntity.ok().body(jwt);
            }
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    public ResponseEntity<String> signupAsUser(User user) throws Exception{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().body("signed-in successfully");
    }
}
