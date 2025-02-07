package com.sayit.shadhi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    @JsonProperty("userName")
    private  String userName;

    @JsonProperty("password")
    private String password;

}
