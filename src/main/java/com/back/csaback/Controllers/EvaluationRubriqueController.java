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

import java.util.List;
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

        List<RubriqueEvaluation> listrub= evaluationRubriqueService.getByRubriqueAndEval(rub,eval);
        if(!listrub.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cette rubrique est deja dans l'evaluation");

        //verifier si la rubrique est non composé
        if(!rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubrique).isEmpty())
            return ResponseEntity.badRequest().body("Cette rubrique est composé, veuillez choisir une rubrique non composé");

        RubriqueEvaluation rubeval;
        try {
            rubeval = evaluationRubriqueService.attachRubriqueToEval(evaluation,rubrique,ordre.shortValue(),designation);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'evaluation n'est pas en cours d'elaboration");
        }
        //attacher la rubrique a l'evaluation
            if(rubeval!= null) return ResponseEntity.ok(rubeval);
            return ResponseEntity.badRequest().body("Erreur lors de l'attachement de la rubrique a l'evaluation");
    }


    /**
     * exemple:
     * localhost:8080/evaluations/rsnc/ordonner?id=29&ordre=4
     * @param id
     * @param ordre
     * @return
     */
    @PreAuthorize("hasRole('ENS')")
    @PostMapping("/rsnc/ordonner")
    public ResponseEntity<?> ordonner_rsnc(@RequestParam("id") Integer id,@RequestParam("ordre") Integer ordre) {

        RubriqueEvaluation rubriqueEvaluation;

        if (ordre >99) return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("la valeur de l'ordre est superieur au prerequis");
        try{
            rubriqueEvaluation = evaluationRubriqueService.ordonnerRubriqueInEval(id,ordre);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rubrique inexistante");
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'évaluation n'est plus en cours d'elaboration");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'ordre donné n'est pas valide");
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
        catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'evaluation n'est plus en cours d'elaboration");
        }
    }

    /**
     * exemple:
     * http://localhost:8080/evaluations/rsnc
     * @return
     */
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/rsnc")
    public ResponseEntity<List<RubriqueEvaluation>> listAll() {
        // Vérifier si la rubrique à supprimer existe
        return ResponseEntity.ok(evaluationRubriqueService.getAll());
    }

    /**
     * exemple:
     * http://localhost:8080/evaluations/rsnc/29
     * @return
     */
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/rsnc/{id}")
    public ResponseEntity<RubriqueEvaluation> getById(@PathVariable("id") Integer id) {
        return  ResponseEntity.ok(evaluationRubriqueService.getById(id));
    }

    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/rsnc/findbyIdEvalAndIdRubrique")
    public ResponseEntity<List<RubriqueEvaluation>> getByIdRubriqueAndIdEval(@RequestParam("ideval") Integer ideval,@RequestParam("idrubrique") Integer idrubrique){

        Evaluation eval = evaluationService.findById(ideval);
        Rubrique rubrique = rubriqueService.findById(idrubrique);
        return ResponseEntity.ok(evaluationRubriqueService.getByRubriqueAndEval(rubrique,eval));
    }
}


