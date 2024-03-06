package com.back.csaback.Controllers;


import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Services.QuestionEvaluationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Contrôleur REST responsable de la gestion des requêtes liées aux questions evaluation.
 * Ce contrôleur offre des endpoints pour la création, la récupération, l'ordonnancement'et la suppression de questions des evaluations,
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 04/03/2024
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/eva/quv")
public class QuestionEvaluationController {
    @Autowired
    QuestionEvaluationService questionEvaluationService;

    /**
     *
     * {
     *   "id": 0,
     *   "idRubriqueEvaluation": {"id":0},
     *   "idQuestion": {"id":0},
     *   "idQualificatif": {"id":0},
     *   "ordre": 0,
     *   "intitule": ""   //optionnel
     * }
     *
     *
     *
     * @param newQuestion
     *
     * @return
     */
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
    @PreAuthorize("hasRole('ENS')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            questionEvaluationService.delete(id);
            return ResponseEntity.ok("question supprimée");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()) ;
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/all")
    public ResponseEntity<List<QuestionEvaluation>> getAll() {
        List<QuestionEvaluation> questions= questionEvaluationService.getAll();
        return  ResponseEntity.ok(questions);
    }
    /**
     * [
     *     {
     *         "id": 1,
     *         "idRubriqueEvaluation": {},  // optionnel
     *         "idQuestion": {},            //optionnel
     *         "idQualificatif": {},        //optionnel
     *         "ordre": 0,
     *         "intitule": ""               //optionnel
     *     }
     * ]
     * @param questionEvaluations
     */
    @PreAuthorize("hasRole('ENS')")
    @PutMapping("/ordonner")
    public ResponseEntity<?> ordonner(@Valid @RequestBody List<QuestionEvaluation> questionEvaluations, BindingResult bindingResult) {
        for (QuestionEvaluation quesEva : questionEvaluations ){
            if (bindingResult.hasFieldErrors("ordre")) {
                return ResponseEntity.badRequest().body("Erreur de validation: " + bindingResult.getFieldError("ordre").getDefaultMessage());
            }else {
                try {
                    QuestionEvaluation questionEvaluation=questionEvaluationService.findById(quesEva.getId());
                    questionEvaluation.setOrdre(quesEva.getOrdre());
                    questionEvaluationService.update(questionEvaluation);
                }catch (EntityNotFoundException ex) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
                }
            }
        }
        return getAll();
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/findByIdRubrique/{id}")
    public ResponseEntity<?> getAllByIdRubrique(@PathVariable Integer id) {
        try {
            List<QuestionEvaluation> questionEvaluations = questionEvaluationService.findByIdRubrique(id);
            return ResponseEntity.ok(questionEvaluations);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}

