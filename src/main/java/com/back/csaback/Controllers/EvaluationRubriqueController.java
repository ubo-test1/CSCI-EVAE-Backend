package com.back.csaback.Controllers;

import com.back.csaback.DTO.RubriqueEvaluationDTO;
import com.back.csaback.Exceptions.ErrorRubriqueEvaluationAlreadyExist;
import com.back.csaback.Models.*;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import com.back.csaback.Services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/eva/rbs")
public class EvaluationRubriqueController {
    @Autowired
    private EvaluationRubriqueService evaluationRubriqueService;
    @Autowired
    private EvaluationService es;
    @Autowired
    private Tooltip ttip;
    @Autowired
    private RubriqueService rs;
    @Autowired
    private RubriqueEvaluationRepository rer;
    @Autowired
    private QuestionEvaluationRepository qer;

 /*   {
        "idRubrique": {
        "id": 8
    },
        "idEvaluation": {
        "id": 21
    },
        "ordre": 2,
        "designation": "test"
    }*/
//    @PreAuthorize("hasRole('ENS')")
//    @PostMapping("/add")
//    public ResponseEntity<?> ajouter_rsnc(@Valid @RequestBody RubriqueEvaluation newRubriqueEvaluation, BindingResult bindingResult) {
//        try {
//            if (bindingResult.hasErrors()) {
//                return ResponseEntity.badRequest().body("Erreur de validation : " + bindingResult.getFieldError().getDefaultMessage());
//            } else {
//                evaluationRubriqueService.save(newRubriqueEvaluation);
//                return ResponseEntity.status(201).body("rubrique créé avec succès");
//            }
//        } catch (EntityNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//    }


    @PreAuthorize("hasRole('ENS')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
         evaluationRubriqueService.delete(id);
            return ResponseEntity.ok("rubrique supprimée");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()) ;
        }
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/all")
    public ResponseEntity<List<RubriqueEvaluation>> getAll() {
        List<RubriqueEvaluation> rubriques= evaluationRubriqueService.getAll();
        System.out.println(rubriques.size());
        System.out.println(rubriques.get(1).getOrdre());
        System.out.println(rubriques.get(0).getIdRubrique().getId());

        return  ResponseEntity.ok(rubriques);
    }

    /**
     * [
     *  {
     *      "id":1,
     *      "ordre":2
     *  },
     *  {
     *      "id":2,
     *      "ordre" : 3
     *  }
     * ]
     * @param rubriqueEvaluations
     * @return
     */
    @PreAuthorize("hasRole('ENS')")
    @PutMapping("/ordonner")
    public ResponseEntity<?> ordonner(@RequestBody List<RubriqueEvaluationDTO> rubriqueEvaluations) {
        for (RubriqueEvaluationDTO rubEva : rubriqueEvaluations ){
                try {
                    if(rubEva.getId()==null) throw new IllegalArgumentException("id obligatoire !!");
                    if(rubEva.getOrdre()==null) throw new IllegalArgumentException("ordre obligatoire !!");
                    RubriqueEvaluation rubriqueEvaluation=evaluationRubriqueService.findById(rubEva.getId());
                    rubriqueEvaluation.setOrdre(rubEva.getOrdre());
                    evaluationRubriqueService.update(rubriqueEvaluation);
                }catch (EntityNotFoundException ex) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
                }catch (IllegalArgumentException ex) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
                }
        }
        return getAll();
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return  ResponseEntity.ok("test bien passé ");
    }
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/findByIdEvaluation/{id}")
    public ResponseEntity<?> getAllByIdEvaluation(@PathVariable Integer id) {
        try {
            return  ResponseEntity.ok(evaluationRubriqueService.findAllByIdEvaluation(id));
        }catch (EntityNotFoundException ex){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    /**
     * {
     *     "eva" : 1,
     *     "rub" : 2
     * }
     * @param auth
     * @param req
     * @return
     */
    @PreAuthorize("hasRole('ENS')")
    @PostMapping("addRub")
    public ResponseEntity<?> ajouterRub(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody HashMap<String,String> req){
        try{
            Evaluation e = es.findById(Integer.parseInt((req.get("eva"))));
            if(!es.checkEvaOwnership(ttip.getUserFromToken(auth),e)) return ResponseEntity.badRequest().body("L'evaluation n'appartient pas a l'enseignant");
            Rubrique r = rs.findById(Integer.parseInt(req.get("rub")));
            RubriqueEvaluation re = new RubriqueEvaluation();
            re.setIdEvaluation(e);
            re.setIdRubrique(r);
            re.setOrdre((short) (rs.getLastOrdreRE(e)+1));
            re.setDesignation(r.getDesignation());
            rs.migrateQuestions(rer.save(re),r);
            return ResponseEntity.ok("Questions ajoute avec succes");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ENS')")
    @GetMapping("delRub/{id}")
    public ResponseEntity<?> delRub(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable("id") int id_rubEva){
        try {
            RubriqueEvaluation r = rer.findById(id_rubEva).get();
            if (!es.checkEvaOwnership(ttip.getUserFromToken(auth), r))
                return ResponseEntity.badRequest().body("L'evaluation n'appartient pas a l'enseignant");
            qer.deleteAllByIdRubriqueEvaluation(r.getId());
            rs.deleteQuestionsRub(r);
            evaluationRubriqueService.pruneOrdre(r.getIdEvaluation());
            return ResponseEntity.ok("Supprime avec succes");
        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
            return ResponseEntity.status(401).body("Rubrique liee, suppression impossible!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}

