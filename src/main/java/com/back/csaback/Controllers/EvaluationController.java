package com.back.csaback.Controllers;

import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.Models.*;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Services.ElementConstitutifService;
import com.back.csaback.Services.EnseignantService;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.PromotionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("eva")
@CrossOrigin(origins = "http://localhost:3000")
public class EvaluationController {

    @Autowired
    private EvaluationService es;
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private ElementConstitutifService elementConstitutifService;
    @Autowired
    private PromotionService promotionService;

    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("getAll")
    public ResponseEntity<List<EvaDTO>> getAll(){
        try{
            List<EvaDTO> ret = new ArrayList<>();
            EvaDTO tmp;
            for(Evaluation e : es.getAll()){
                tmp = new EvaDTO(e);
                ret.add(tmp);
            }
            return ResponseEntity.ok(ret);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("consulterInfo/{id}")
    public ResponseEntity<EvaluationDetails> consulterInfo(@PathVariable("id") Integer id){
        try{
            Evaluation e = es.findById(id).orElse(null);
            if(e == null )throw new IllegalArgumentException("Evaluation introuvable");
            EvaluationDetails ret = es.ConsulterEvaluation(e);
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
    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @PostMapping("/create")
    public ResponseEntity<?> createEvae(@RequestBody HashMap<String, Object> req) {
        try {
            // Retrieve Enseignant
            Object enseignantObj = req.get("noEnseignant");
            if (enseignantObj == null) {
                return ResponseEntity.badRequest().body("Le champ 'noEnseignant' est requis !");
            }
            Enseignant enseignant = enseignantService.findById(parseInt(enseignantObj.toString())).orElse(null);
            if (enseignant == null) {
                return ResponseEntity.badRequest().body("Enseignant non trouvé !");
            }

            // Retrieve ElementConstitutifId
            Object codeFormationObj = req.get("codeFormation");
            Object codeUeObj = req.get("codeUe");
            Object codeEcObj = req.get("codeEc");
            if (codeFormationObj == null || codeUeObj == null || codeEcObj == null) {
                return ResponseEntity.badRequest().body("Les champs 'codeFormation', 'codeUe' et 'codeEc' sont requis !");
            }
            ElementConstitutifId elementConstitutifId = new ElementConstitutifId();
            elementConstitutifId.setCodeFormation(codeFormationObj.toString());
            elementConstitutifId.setCodeUe(codeUeObj.toString());
            elementConstitutifId.setCodeEc(codeEcObj.toString());

            // Retrieve ElementConstitutif
            Optional<ElementConstitutif> elementConstitutifOpt = elementConstitutifService.findById(elementConstitutifId);
            if (!elementConstitutifOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Element constitutif non trouvé !");
            }
            ElementConstitutif elementConstitutif = elementConstitutifOpt.get();

            // Create Evaluation
            Object anneeUniversitaire=req.get("anneeUniversitaire");
            PromotionId promotionId=new PromotionId();
            promotionId.setCodeFormation(codeFormationObj.toString());
            promotionId.setAnneeUniversitaire(anneeUniversitaire.toString());
            Promotion promotion= promotionService.findById(promotionId).orElse(null);
            if(promotion==null){
                return ResponseEntity.badRequest().body("Evaluation non trouvé !");
            }
            Object idObj = req.get("id");
            Object noEvaluationObj = req.get("noEvaluation");
            Object designationObj = req.get("designation");
            Object etatObj = req.get("etat");
            Object periodeObj = req.get("periode");
            Object debutReponseObj = req.get("debutReponse");
            Object finReponseObj = req.get("finReponse");

            if (idObj == null || noEvaluationObj == null || designationObj == null || etatObj == null || periodeObj == null || debutReponseObj == null || finReponseObj == null) {
                return ResponseEntity.badRequest().body("Les champs 'id', 'noEvaluation', 'designation', 'etat', 'periode', 'debutReponse' et 'finReponse' sont requis !");
            }

            Evaluation evaluation = new Evaluation();
            evaluation.setId(Integer.parseInt(idObj.toString()));
            evaluation.setElementConstitutif(elementConstitutif);
            evaluation.setNoEnseignant(enseignant);
            evaluation.setNoEvaluation(Short.valueOf(noEvaluationObj.toString()));
            evaluation.setDesignation(designationObj.toString());
            evaluation.setEtat(etatObj.toString());
            evaluation.setPeriode(periodeObj.toString());
            evaluation.setPromotion(promotion);
            evaluation.setDebutReponse(LocalDate.parse(debutReponseObj.toString()));
            evaluation.setFinReponse(LocalDate.parse(finReponseObj.toString()));

            // Check if evaluation with the same ID already exists
            Optional<Evaluation> existingEvaluation = es.findById(evaluation.getId());
            if (existingEvaluation.isPresent()) {
                return ResponseEntity.badRequest().body("Evaluation déjà existante !");
            }

            // Create the evaluation
            Evaluation evaluationCree = this.es.createEvaluation(evaluation);
            return ResponseEntity.ok(evaluationCree);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }




    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @PutMapping("/update")
    public ResponseEntity<?> updateEvaluation(@RequestBody Evaluation evaluation) {
        try {
            Evaluation updatedEvaluation = es.updateEvaluation(evaluation);
            return ResponseEntity.ok(updatedEvaluation);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @DeleteMapping("/delete/{noEvaluation}")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Integer noEvaluation) {
        try {
            Evaluation evaluation=es.findById(noEvaluation).orElse(null);
            es.deleteEvaluation(evaluation);
            return ResponseEntity.ok("L'évaluation a été supprimée avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de l'évaluation.");
        }
    }
    }
