package com.back.csaback.Controllers;


import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Services.ElementConstitutifService;
import com.back.csaback.Services.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/ElementConstitutif"})
@CrossOrigin(origins = "http://localhost:3000")
public class ElementConstitutifController {
    @Autowired
    ElementConstitutifService elementConstitutifService;
    @Autowired
    Tooltip ttip;
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("getAllByUE")
    public ResponseEntity<List<ElementConstitutif>> getAllByEnseignant(@RequestBody UniteEnseignement ue){
        return ResponseEntity.ok(elementConstitutifService.getAllByEnseigant(ue));

    }
}
