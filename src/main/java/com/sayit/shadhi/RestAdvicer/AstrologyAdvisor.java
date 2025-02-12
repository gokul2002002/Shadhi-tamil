package com.sayit.shadhi.RestAdvicer;

import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import com.sayit.shadhi.Exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AstrologyAdvisor {

     @ExceptionHandler(ChartNotFoundException.class)
     public ResponseEntity<String> sendChartNotFound(ChartNotFoundException chartNotFoundException){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chartNotFoundException.getMessage());
     }

     @ExceptionHandler(UserAlreadyExistException.class)
     public ResponseEntity<String> sendUserAlreadyExist(UserAlreadyExistException userAlreadyExistException){
          return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExistException.getMessage());
     }

}
