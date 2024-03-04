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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Autowired
    private RubriqueQuestionService rubriqueQuestionService;


/**
*        exemple:
*        {
*            "evaluation":1,
*            "rubrique":1,
*            "ordre":5,
*            "designation":"test" (optionel)
*        }
 *        */
 @PreAuthorize("hasRole('ENS')")
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


    /**
     * exemple:
     * localhost:8080/evaluations/rsnc/29/5
     * @param idRubEval
     * @param ordre
     * @return
     */
    @PreAuthorize("hasRole('ENS')")
    @PostMapping("/rsnc/{idRubEval}/{ordre}")
    public ResponseEntity<?> ordonner_rsnc(@PathVariable("idRubEval") Integer idRubEval,@PathVariable("ordre") Integer ordre) {
        RubriqueEvaluation rubriqueEvaluation;
        if (ordre >99) return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("la valeur de l'ordre est superieur au prerequis");
        try{
            rubriqueEvaluation = evaluationRubriqueService.ordonnerRubriqueInEval(idRubEval,ordre);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluation inexistante");
        }
        return ResponseEntity.ok(rubriqueEvaluation);
    }

    /**
     *   exemple:
     *   localhost:8080/evaluations/rsnc/{idRubriqueEvaluation}
     */
    @PreAuthorize("hasRole('ENS')")
    @DeleteMapping("/rsnc/{id}")
    public ResponseEntity<?> supprimer_rsnc(@PathVariable("id") Integer id) {
        // Vérifier si la rubrique à supprimer existe
        try{
            evaluationRubriqueService.detachRubriqueFromEval(id);
            return ResponseEntity.ok().body("Rubrique no "+id+" détachée");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La rubrique no "+id+" est introuvable.");
        }
    }


}


