package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.DTOs.ChartRequestDTO;
import com.sayit.shadhi.DTOs.ChartScoreDTO;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import com.sayit.shadhi.Services.AstrologyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/astrology")
public class AstrologyController {

    private final AstrologyService astrologyService;

    @GetMapping("/get/documents")
    public ResponseEntity<?> getAstrologyDocument(@RequestBody ChartRequestDTO chartRequestDTO){
        try {
            return ResponseEntity.ok().body(
                    astrologyService.getChartdocuments(chartRequestDTO)
            );
        }catch (ChartNotFoundException ce){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ce.getLocalizedMessage());
        }
    }

    @PostMapping("/give-score")
    public ResponseEntity<?> giveScoreToUsers(@RequestBody ChartScoreDTO chartScoreDTO){
        try {
            return ResponseEntity.ok().body("Score Successfully updated");
        }catch (RuntimeException re){
            return ResponseEntity.badRequest().body("not valid request");
        }
    }
}
