package com.sayit.shadhi.RestAdvicer;

import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AstrologyAdvisor {

     @ExceptionHandler(ChartNotFoundException.class)
     public ResponseEntity<String> sendChartNotFound(ChartNotFoundException chartNotFoundException){
          return ResponseEntity.ok(chartNotFoundException.getMessage());
     }


}
