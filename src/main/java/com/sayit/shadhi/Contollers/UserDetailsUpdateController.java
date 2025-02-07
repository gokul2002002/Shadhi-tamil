package com.sayit.shadhi.Contollers;

import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.NotValidInformation;
import com.sayit.shadhi.Repositories.UserRepository;
import com.sayit.shadhi.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
@AllArgsConstructor
public class UserDetailsUpdateController {

    private final UserService userService;

    @PutMapping("/detail")
    public ResponseEntity<String> updateNameOfAnUser(
            @RequestParam  String toChange,
            @RequestParam String value
    ){
        try {
            return ResponseEntity.ok().body("${toChange} Changes Successfully");
        }catch (NotValidInformation ve){
            return ResponseEntity.badRequest().body(ve.getLocalizedMessage());
        }
    }

    @PostMapping("/upload/photos")
    public ResponseEntity<String> updatePictures(){
        return null;
    }
}
