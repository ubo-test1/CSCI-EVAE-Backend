package com.back.csaback.Controllers;


import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Services.EnseignantService;
import com.back.csaback.Services.RubriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/rubrique")
public class RubriqueController {
    @Autowired
    private RubriqueService rubriqueService;
    @Autowired
    private EnseignantService enseignantService;

    @PostMapping("/create")
    public ResponseEntity<Rubrique> saveRubrique(@RequestBody HashMap<String,String> req) {
        Long id= Long.parseLong(req.get("id"));
        String type= req.get("type");
        String designation=req.get("designation");
        Long noEnseignant= Long.parseLong(req.get("noEnseignant"));
        Double ordre=Double.parseDouble(req.get("ordre"));
        Enseignant enseignant=enseignantService.findById(noEnseignant).orElse(null);
        if(enseignant==null){
            throw new IllegalArgumentException("L'enseignant' spécifié n'existe pas.");
        }
        Rubrique rubrique=new Rubrique();
        rubrique.setId(id);
        rubrique.setType(type);
        rubrique.setDesignation(designation);
        rubrique.setOrdre(ordre);
        rubrique.setNoEnseignant(enseignant);

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
