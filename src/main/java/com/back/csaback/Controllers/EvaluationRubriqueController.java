package com.back.csaback.Controllers;

import com.back.csaback.Exceptions.ErrorRubriqueEvaluationAlreadyExist;
import com.back.csaback.Models.*;
import com.back.csaback.Services.EvaluationRubriqueService;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.RubriqueQuestionService;
import com.back.csaback.Services.RubriqueService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/eva/rbs")
public class EvaluationRubriqueController {
    @Autowired
    private EvaluationRubriqueService evaluationRubriqueService;
    @PreAuthorize("hasRole('ENS')")
    @PostMapping("/add")
    public ResponseEntity<?> ajouter_rsnc(@Valid @RequestBody RubriqueEvaluation newRubriqueEvaluation, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body("Erreur de validation : " + bindingResult.getFieldError().getDefaultMessage());
            } else {
                evaluationRubriqueService.save(newRubriqueEvaluation);
                return ResponseEntity.status(201).body("rubrique créé avec succès");
            }
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @PreAuthorize("hasRole('ENS')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
         evaluationRubriqueService.delete(id);
            return ResponseEntity.ok("question supprimée");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()) ;
        }
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<RubriqueEvaluation> questions= evaluationRubriqueService.getAll();
        return  ResponseEntity.ok(questions);
    }
    @PreAuthorize("hasRole('ENS')")
    @PutMapping("/ordonner")
    public ResponseEntity<?> ordonner(@Valid @RequestBody List<RubriqueEvaluation> rubriqueEvaluations, BindingResult bindingResult) {
        for (RubriqueEvaluation rubEva : rubriqueEvaluations ){
            if (bindingResult.hasFieldErrors("ordre")) {
                return ResponseEntity.badRequest().body("Erreur de validation: " + bindingResult.getFieldError("ordre").getDefaultMessage());
            }else {
                try {
                    RubriqueEvaluation rubriqueEvaluation=evaluationRubriqueService.findById(rubEva.getId());
                    rubriqueEvaluation.setOrdre(rubEva.getOrdre());
                    evaluationRubriqueService.update(rubriqueEvaluation);
                }catch (EntityNotFoundException ex) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
                }
            }
        }
        return getAll();
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return  ResponseEntity.ok("test bien passé ");
    }



}


/*
@PreAuthorize("hasRole('ENS')")
    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody QuestionEvaluation newQuestion, BindingResult bindingResult) {
        try{ if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation : " + bindingResult.getFieldError().getDefaultMessage());
        }else {
            questionEvaluationService.save(newQuestion);
            return ResponseEntity.status(201).body("question créé avec succès");
        }
        }catch (ErrorQuestionAlreadyExist ex)  {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
        Integer evaluation;
        Integer rubrique;
        String designation = requestBody.get("designation");

        try {
            evaluation = Integer.parseInt(requestBody.get("evaluation"));
            rubrique = Integer.parseInt(requestBody.get("rubrique"));
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
            rubeval = evaluationRubriqueService.attachRubriqueToEval(evaluation,rubrique,designation);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'evaluation n'est pas en cours d'elaboration");
        }
        //attacher la rubrique a l'evaluation
            if(rubeval!= null) return ResponseEntity.ok(rubeval);
            return ResponseEntity.badRequest().body("Erreur lors de l'attachement de la rubrique a l'evaluation");*/





/**
 * exemple:
 * localhost:8080/evaluations/rsnc/ordonner
 *{
 *       "id": 61,
 *       "ordre": 2
 *}
 */
    /*@PreAuthorize("hasRole('ENS')")
    @PutMapping("/rsnc/ordonner")
    public ResponseEntity<?> ordonner_rsnc(@RequestBody Map<String, String> requestBody) {

        Integer id;
        Integer ordre;

        try {
            id = Integer.parseInt(requestBody.get("id"));
            ordre = Integer.parseInt(requestBody.get("ordre"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Veuillez saisir tous les champs necessaires au format correcte");
        }


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

    *//**
 *   exemple:
 *   localhost:8080/evaluations/rsnc/{idRubriqueEvaluation}
 *//*
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

    *//**
 * exemple:
 * http://localhost:8080/evaluations/rsnc
 * @return
 *//*
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/rsnc")
    public ResponseEntity<List<RubriqueEvaluation>> listAll() {
        // Vérifier si la rubrique à supprimer existe
        return ResponseEntity.ok(evaluationRubriqueService.getAll());
    }

    *//**
 * exemple:
 * http://localhost:8080/evaluations/rsnc/29
 * @return
 *//*

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

    *//**
 *   http://localhost:8080/evaluations/rsnc/findbyeval?ideval=1
 *//*
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/rsnc/findbyeval")
    public ResponseEntity<List<RubriqueEvaluation>> getByEval(@RequestParam("ideval") Integer ideval){

        Evaluation eval = evaluationService.findById(ideval);
        return ResponseEntity.ok(evaluationRubriqueService.getRubriqueByEval(eval));
    }*/

