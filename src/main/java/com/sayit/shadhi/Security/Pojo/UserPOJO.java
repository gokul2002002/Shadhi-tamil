package com.sayit.shadhi.Security.Pojo;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPOJO {

    private Boolean isValid;

    private UserDetails userDetails;
}
