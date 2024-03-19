package com.back.csaback.Controllers;


import com.back.csaback.DTO.EvaRubDetails;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.QuestionEvaluationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    EvaluationService es;
    @Autowired
    private QuestionEvaluationRepository qer;
    @Autowired
    private QuestionRepository qr;
    @Autowired
    private EvaRubRepository err;

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

    @PreAuthorize("hasRole('ENS') || hasRole('ADM') || hasRole('ETU')")
    @GetMapping("consulterInfo/{id}")
    public ResponseEntity<EvaRubDetails> consulterInfo(@PathVariable("id") Integer id){
        try{
            Evaluation e = es.findById(id);
            if(e == null )throw new IllegalArgumentException("Evaluation introuvable");
            EvaRubDetails ret = es.ConsulterEvaRub(e);
            if(ret == null) throw new IllegalStateException("Erreur serveur");
            return ResponseEntity.ok(ret);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch(Exception ee){
            ee.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * {
     *     "id_rubEva": 1,
     *     "qList": [
     *          1,2,3..
     *     ]
     * }
     * @param req
     * @return
     */
    @PreAuthorize("hasRole('ENS') || hasRole('ETU')")
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody HashMap<String, Object> req){
        try{
            int id = Integer.parseInt(""+req.get("id_rubEva"));
            RubriqueEvaluation re = err.findById(id).get();
            List<Integer> qList = (List<Integer>) req.get("qList");
            for(int i : qList){
                QuestionEvaluation tmp = new QuestionEvaluation();
                tmp.setIdQuestion(qr.findById(i).get());
                tmp.setIdRubriqueEvaluation(re);
                if(qer.findLatestOrdreByRubriqueEvaluationId(re.getId())==null){
                    tmp.setOrdre((short) 1);
                }else{
                    tmp.setOrdre((short) (qer.findLatestOrdreByRubriqueEvaluationId(re.getId())+1));
                }
                qer.save(tmp);
            }
            return ResponseEntity.ok("Questions ajoutes avec succes");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

