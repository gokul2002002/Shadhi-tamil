package com.sayit.shadhi.Contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/request")
@RestController
public class RequestController {

    @PutMapping
    public ResponseEntity<String> giveRequestToPair(){
            return null;
    }

    @PutMapping("/accept")
    public ResponseEntity<String> AcceptRequest(@RequestParam long accessId){
        return null;
    }

    @PutMapping("/birth-chart/")
    public ResponseEntity<String> giveRequestToBirthChart(@RequestParam long userId){
        return null;
    }
}
