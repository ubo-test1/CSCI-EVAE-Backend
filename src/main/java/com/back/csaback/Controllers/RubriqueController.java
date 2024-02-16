package com.back.csaback.Controllers;


import com.back.csaback.Models.Rubrique;
import com.back.csaback.Services.RubriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rubrique")
public class RubriqueController {
    @Autowired
    private RubriqueService rubriqueService;

    @PostMapping("/create")
    public ResponseEntity<Rubrique> saveRubrique(@RequestBody Rubrique rubrique) {
        try {
            Rubrique savedRubrique = rubriqueService.saveRubrique(rubrique);
            return new ResponseEntity<>(savedRubrique, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<Rubrique>> getAllRubriques() {
        try {
            List<Rubrique> rubriques = rubriqueService.getAllRubriques();
            return new ResponseEntity<>(rubriques, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            // Re-throw the exception as it is
            throw e;
        }
    }

}
