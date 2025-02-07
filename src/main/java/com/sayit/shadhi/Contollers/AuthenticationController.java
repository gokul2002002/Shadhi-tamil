package com.sayit.shadhi.Contollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sayit.shadhi.DTOs.LoginDTO;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Services.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtpForVerification(@RequestParam String mobileNumber){
        return authenticationService.sendOtpForVerification();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupAsUser(@RequestBody User user){
        try{
            return authenticationService.signupAsUser(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAsuser(@RequestBody LoginDTO userDetail){
        try{
            return authenticationService.isUserisValid(userDetail);
        }catch (UsernameNotFoundException | JsonProcessingException j){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(j.getLocalizedMessage());
        }
    }
}
