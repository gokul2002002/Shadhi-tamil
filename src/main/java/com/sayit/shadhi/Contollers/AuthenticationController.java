package com.sayit.shadhi.Contollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sayit.shadhi.DTOs.LoginDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Security.Authentication.ContextImplementation;
import com.sayit.shadhi.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtpForVerification(@RequestParam String email){
        return authenticationService.sendOtpForVerification(email);
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
      Authentication authentication =  new ContextImplementation().getAuthentication();
        try{
            return authenticationService.isUserisValid(userDetail);
        }catch (UsernameNotFoundException | JsonProcessingException j){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(j.getLocalizedMessage());
        }
    }

    @PutMapping("/verify/OTP")
    public GeneralStatus verifyOtp(@RequestParam String mail , @RequestParam String OTP){
        return authenticationService.verifyOtp(mail , OTP);
    }

    @PostMapping("/sign-up/astrologer")
    public ResponseEntity<String> signupAsAstrologer(@RequestBody User user) throws Exception {
        return authenticationService.signupAsUser(user);
    }

}
