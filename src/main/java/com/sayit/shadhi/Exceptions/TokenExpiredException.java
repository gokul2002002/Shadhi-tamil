package com.sayit.shadhi.Exceptions;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {

        super(message);
    }
}
