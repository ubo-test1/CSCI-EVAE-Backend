package com.back.csaback.Controllers;

import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.Models.*;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import com.back.csaback.Services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.tools.Tool;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("eva")
@CrossOrigin
public class EvaluationController {

    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    EvaluationRepository er;
    @Autowired
    private ElementConstitutifService elementConstitutifService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private EvaluationService es;
    @Autowired
    private RubriqueService rs;
    @Autowired
    private Tooltip ttip;
    @Autowired
    private RubriqueEvaluationRepository rer;

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
    @GetMapping("testAll")
    public List<Evaluation> getAllE(){
       return  er.findAll();
    }

    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("test")
    public String testAll(){
        return  "teeeeeeeeeeeeeeeeeeeeeeeeeest";
    }

    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("consulterInfo/{id}")
    public ResponseEntity<EvaluationDetails> consulterInfo(@PathVariable("id") Integer id){
        try{
            Evaluation e = es.findById(id);
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
    @PreAuthorize("hasRole('ETU')")
    @GetMapping("getByPro")
    public ResponseEntity<?> getByPro(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        try {
            Etudiant e = ttip.getEtudFromToken(auth);
            return ResponseEntity.ok(es.getAllEvaluations(e));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
 /*   @PreAuthorize("hasRole('ENS')")
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
            if (codeFormationObj == null || codeUeObj == null ) {
                return ResponseEntity.badRequest().body("Les champs 'codeFormation', 'codeUe' et 'codeEc' sont requis !");
            }
            ElementConstitutifId elementConstitutifId = new ElementConstitutifId();
            elementConstitutifId.setCodeFormation(codeFormationObj.toString());
            elementConstitutifId.setCodeUe(codeUeObj.toString());
            //elementConstitutifId.setCodeEc(codeEcObj.toString());

            // Retrieve ElementConstitutif
*//*            Optional<ElementConstitutif> elementConstitutifOpt = elementConstitutifService.findById(elementConstitutifId);

            ElementConstitutif elementConstitutif = elementConstitutifOpt.get();*//*

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

            if (idObj == null || noEvaluationObj == null || designationObj == null || etatObj == null  || debutReponseObj == null || finReponseObj == null) {
                return ResponseEntity.badRequest().body("Les champs 'id', 'noEvaluation', 'designation', 'etat', 'periode', 'debutReponse' et 'finReponse' sont requis !");
            }

            Evaluation evaluation = new Evaluation();
            evaluation.setId(Integer.parseInt(idObj.toString()));
            evaluation.setElementConstitutif(new ElementConstitutif());
            evaluation.setNoEnseignant(enseignant);
            evaluation.setNoEvaluation(Short.valueOf(noEvaluationObj.toString()));
            evaluation.setDesignation(designationObj.toString());
            evaluation.setEtat(etatObj.toString());
            evaluation.setPeriode(periodeObj.toString());
            evaluation.setPromotion(promotion);
            evaluation.setDebutReponse(LocalDate.parse(debutReponseObj.toString()));
            evaluation.setFinReponse(LocalDate.parse(finReponseObj.toString()));

            // Check if evaluation with the same ID already exists
            Optional<Evaluation> existingEvaluation = es.findByIdOpt(evaluation.getId());
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
    }*/
    /**
     * {
     *         "uniteEnseignement": {
     *             "id": {
     *                 "codeFormation": "M2DOSI",
     *                 "codeUe": "J2EE"
     *             }
     *         },
     *         "elementConstitutif": {   // optionnel
     *             "id": {
     *                 "codeFormation": "M2DOSI",
     *                 "codeUe": "J2EE",
     *                 "codeEc" :"CME"
     *             }
     *         },
     *         "promotion": {
     *             "id": {
     *                 "codeFormation": "M2DOSI",
     *                 "anneeUniversitaire": "2014-2015"
     *             }
     *         },
     *         "designation": "test update 3",
     *         "etat": "CLO",
     *         "periode": "Du 22 septembre au 24 octobre",//opt
     *         "debutReponse": "2014-11-01",
     *         "finReponse": "2014-11-12"
     * }
     * @param auth
     * @param newEvaluation
     * @param bindingResult
     * @return
     */
 @PreAuthorize("hasRole('ENS')")
 @PostMapping("/create")
 public ResponseEntity<?> save(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @Valid @RequestBody Evaluation newEvaluation, BindingResult bindingResult) {
    try {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation : " + bindingResult.getFieldError().getDefaultMessage());
        }else {
            Enseignant e = ttip.getUserFromToken(auth);
            newEvaluation.setNoEnseignant(e);
            es.createEvaluation(newEvaluation);
            return ResponseEntity.status(201).body("Evaluation crée avec succès");
        }
    }catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()) ;
    }


 }

    /**
     * {       "id":"1",
     *         "uniteEnseignement": {
     *             "id": {
     *                 "codeFormation": "M2DOSI",
     *                 "codeUe": "J2EE"
     *             }
     *         },
     *         "elementConstitutif": {   // optionnel
     *             "id": {
     *                 "codeFormation": "M2DOSI",
     *                 "codeUe": "J2EE",
     *                 "codeEc" :"CME"
     *             }
     *         },
     *         "promotion": {
     *             "id": {
     *                 "codeFormation": "M2DOSI",
     *                 "anneeUniversitaire": "2014-2015"
     *             }
     *         },
     *          "noEvaluation": 9,
     *         "designation": "test update 3",
     *         "etat": "CLO",
     *         "periode": "Du 22 septembre au 24 octobre", //opt
     *         "debutReponse": "2014-11-01",
     *         "finReponse": "2014-11-12"
     * }
     * @param auth
     * @param evaluation
     * @param bindingResult
     * @return
     */

    @PreAuthorize("hasRole('ENS')")
    @PutMapping("/update")
    public ResponseEntity<?> updateEvaluation(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,@Valid @RequestBody Evaluation evaluation, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body("Erreur de validation : " + bindingResult.getFieldError().getDefaultMessage());
            }
            else{
                Enseignant e = ttip.getUserFromToken(auth);
                evaluation.setNoEnseignant(e);
            Evaluation updatedEvaluation = es.updateEvaluation(evaluation);
            return ResponseEntity.ok(updatedEvaluation);
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()) ;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ENS')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Integer id) {
        try {
            /*Evaluation evaluation=es.findById(id);
            System.out.println(evaluation.getDesignation());*/
            es.deleteEvaluation(id);
            return ResponseEntity.ok("L'évaluation a été supprimée avec succès.");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de l'évaluation.");
        }
    }

    @PreAuthorize("hasRole('ENS')")
    @DeleteMapping("/testfind/{id}")
    public ResponseEntity<?> TestEvaluation(@PathVariable Integer id) {
            return ResponseEntity.ok(es.findById(id));
    }

    @PreAuthorize("hasRole('ENS')")
    @PostMapping("addRub")
    public ResponseEntity<?> ajouterRub(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,@RequestBody HashMap<String,String> req){
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
        try{
            RubriqueEvaluation r = rer.findById(id_rubEva).get();
            if(!es.checkEvaOwnership(ttip.getUserFromToken(auth),r)) return ResponseEntity.badRequest().body("L'evaluation n'appartient pas a l'enseignant");
            rs.deleteQuestionsRub(r);
            return ResponseEntity.ok("Supprime avec succes");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}