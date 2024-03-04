package com.back.csaback.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("etu")
public class EtudiantController {
    @PreAuthorize("hasRole('ETU')")
    @GetMapping("testEtu")
    public String oui(){
        return "oui";
    }


}
