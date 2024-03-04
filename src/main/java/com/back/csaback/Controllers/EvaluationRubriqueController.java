package com.back.csaback.Controllers;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Services.EvaluationRubriqueService;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.RubriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/evaluations")
@CrossOrigin
public class EvaluationRubriqueController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private RubriqueService rubriqueService;

    @Autowired
    private EvaluationRubriqueService evaluationRubriqueService;


 @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
 @PostMapping("/rsnc")
 public ResponseEntity<?> ajouter_rsnc(@RequestBody Map<String, String> requestBody){

        String evaluation = requestBody.get("evaluation");
        String rubrique = requestBody.get("rubrique");

        return ResponseEntity.ok("ok");
    }
    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("test")
    public String test(){
        return "Evaluation Rubrique Controller is working correctly";
    }
}


