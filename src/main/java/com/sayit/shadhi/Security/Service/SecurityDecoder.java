package com.sayit.shadhi.Security.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayit.shadhi.Exceptions.TokenExpiredException;
import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Security.Pojo.UserPOJO;
import com.sayit.shadhi.Security.UserDetails.UserDetailImp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class SecurityDecoder {

    private final ObjectMapper objectMapper;

    private Boolean isAuthenticated(Date expirationDate){
        return expirationDate.after(new Date());
    }

    public UserPOJO getUserFromJwt(String jwt) throws JsonProcessingException {
       DecodedJWT decodedJWT =  JWT.decode(jwt);
       User user  = objectMapper.readValue(decodedJWT.getSubject() , User.class);
       if(isAuthenticated(decodedJWT.getExpiresAt())){

           return UserPOJO
                   .builder()
                   .isValid(true)
                   .userDetails(new UserDetailImp(user))
                   .build();
       }
       throw new TokenExpiredException("Token expired... log in again");
    }


}
