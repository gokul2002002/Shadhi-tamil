package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.Services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }
    @GetMapping("/get/pair/{type}")
    public ResponseEntity<?> searchPair(@PathVariable String type){
        return null;
    }

    @GetMapping("/get/astrologer")
    public ResponseEntity<?> getAstrologer(){
        return null;
    }

    @PostMapping("/give/request/ast")
    public ResponseEntity<?> giveRequestToAstrologer(){
        return null;
    }
}
