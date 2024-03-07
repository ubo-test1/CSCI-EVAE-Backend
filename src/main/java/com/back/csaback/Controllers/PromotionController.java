package com.back.csaback.Controllers;

import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Promotion;
import com.back.csaback.Services.EnseignantService;
import com.back.csaback.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping({"/promotion"})
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionController {
    @Autowired
    PromotionService promotionService;
    @Autowired
    EnseignantService enseignantService;

    //@PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("/getAll/{noEnseignant}")
    public ResponseEntity<List<Promotion>> getAllbyEnseignant(@PathVariable Integer noEnseignant) {
        try {
            Enseignant enseignant = enseignantService.findById(noEnseignant).orElse(null);
            if (enseignant == null) {
                return ResponseEntity.notFound().build();
            }
            List<Promotion> ret = promotionService.getAllbyEnseignant(enseignant);
            if (ret.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }
            return ResponseEntity.ok(ret);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
