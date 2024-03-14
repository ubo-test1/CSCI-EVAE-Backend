package com.back.csaback.Controllers;


import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Services.Tooltip;
import com.back.csaback.Services.UniteEnseignemantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/UniteUnseignemant"})
@CrossOrigin(origins = "http://localhost:3000")
public class UniteEnseignemantController {

    @Autowired
    UniteEnseignemantService uniteEnseignemantService;
    @Autowired
    Tooltip ttip;

    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/getAllByEnseignant")
    public ResponseEntity<List<UniteEnseignement>> getAllByEnseignant(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        Enseignant e = ttip.getUserFromToken(auth);
        return ResponseEntity.ok(uniteEnseignemantService.getAllByEnseignant(e));
    }


}
