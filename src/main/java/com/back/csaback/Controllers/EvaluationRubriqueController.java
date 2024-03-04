package com.back.csaback.Controllers;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Services.EvaluationRubriqueService;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.RubriqueQuestionService;
import com.back.csaback.Services.RubriqueService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private RubriqueQuestionService rubriqueQuestionService;


/*
        {
            "evaluation":1,
            "rubrique":1,
            "ordre":5,
            "designation":"test" (optionel)
        }
 */
 @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
 @PostMapping("/rsnc")
 public ResponseEntity<?> ajouter_rsnc(@RequestBody Map<String, String> requestBody){

        Integer evaluation;
        Integer rubrique;
        Integer ordre;
        String designation = requestBody.get("designation");
        try {
            evaluation = Integer.parseInt(requestBody.get("evaluation"));
            rubrique = Integer.parseInt(requestBody.get("rubrique"));
            ordre = Integer.parseInt(requestBody.get("ordre"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Veuillez saisir tous les champs necessaires au format correcte");
        }


        //verifier si la rubrique existe
        Rubrique rub;
        try{
            rub = rubriqueService.findById(rubrique);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("la rubrique no "+rubrique+ " est introuvable.");
        }
        if(rub==null) return ResponseEntity.badRequest().body("la rubrique no "+rubrique+ " est introuvable.");


        //verifier si l'evaluation existe
        Evaluation eval;
        try{
            eval = evaluationService.findById(evaluation);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("l'evaluation no "+evaluation+" est introuvable.");
        }
        if(eval==null) return ResponseEntity.badRequest().body("l'evaluation no "+evaluation+" est introuvable.");



        //verifier si la rubrique est non composé
        if(!rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubrique).isEmpty())
            return ResponseEntity.badRequest().body("Cette rubrique est composé, veuillez choisir une rubrique non composé");



        //attacher la rubrique a l'evaluation
        RubriqueEvaluation rubeval = evaluationRubriqueService.attachRubriqueToEval(evaluation,rubrique,ordre.shortValue(),designation);

            if(rubeval!= null) return ResponseEntity.ok(rubeval);
            return ResponseEntity.badRequest().body("Erreur lors de l'attachement de la rubrique a l'evaluation");
    }




    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("test")
    public String test(){
        return "Evaluation Rubrique Controller is working correctly";
    }
}


