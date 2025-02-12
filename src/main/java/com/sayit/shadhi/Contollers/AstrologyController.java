package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.DTOs.AstrologerPriceFilter;
import com.sayit.shadhi.DTOs.ChartRequestDTO;
import com.sayit.shadhi.DTOs.ChartScoreDTO;
import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Security.Authentication.ContextImplementation;
import com.sayit.shadhi.Services.AstrologyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/astrology")
public class AstrologyController {

    private final AstrologyService astrologyService;

    @GetMapping("/get/documents")
    public ResponseEntity<?> getAstrologyDocument(@RequestBody ChartRequestDTO chartRequestDTO){
        Authentication authentication =  new ContextImplementation().getAuthentication();
            return ResponseEntity.ok().body(astrologyService.getChartdocuments(chartRequestDTO));
    }

    @PostMapping("/give-score")
    public ResponseEntity<String> giveScoreToUsers(@RequestBody ChartScoreDTO chartScoreDTO){
            return ResponseEntity.ok().body("Score Successfully updated");
    }

    @GetMapping("/get/all-astrologer")
    public ResponseEntity<List<Astrologer>> getAllAstrologer(){
        return ResponseEntity.ok().body(astrologyService.getAllAstrologer());
    }

    @GetMapping("/get/astrologer/price-range")
    public ResponseEntity<List<Astrologer>> getAstrologerBetweenThePriceRange(@RequestBody AstrologerPriceFilter astrologerPriceFilter){
        return ResponseEntity.ok().body(
                astrologyService.getAstrologerByRange(astrologerPriceFilter));
    }

}
