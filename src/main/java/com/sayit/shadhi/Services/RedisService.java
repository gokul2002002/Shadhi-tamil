package com.sayit.shadhi.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    public String addItem(String userName , String otp , Duration expirationMinitues){
        redisTemplate.opsForValue().set(userName , otp , expirationMinitues);
        return "Sended";
    }

    public String getItem(String userName){
        return (String) redisTemplate.opsForValue().get(userName);
    }
}
