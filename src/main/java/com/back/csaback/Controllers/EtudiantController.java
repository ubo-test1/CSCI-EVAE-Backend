package com.back.csaback.Controllers;

import com.back.csaback.DTO.ConsulterReponseDTO;
import com.back.csaback.Exceptions.ErrorDuplicateReponse;
import com.back.csaback.Exceptions.ErrorEvaluationNoOuverte;
import com.back.csaback.Exceptions.ErrorReponseExists;
import com.back.csaback.Models.ReponseEvaluation;
import com.back.csaback.Models.ReponseQuestion;
import com.back.csaback.Models.ReponseQuestionId;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import com.back.csaback.Repositories.ReponseEvaluationRepository;
import com.back.csaback.Repositories.ReponseQuestionRepository;
import com.back.csaback.Services.EtudiantService;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("etu")
public class EtudiantController {

    @Autowired
    private Tooltip ttip;

    @Autowired
    private EvaluationRepository er;

    @Autowired
    private ReponseEvaluationRepository rer;

    @Autowired
    private QuestionEvaluationRepository qer;

    @Autowired
    private ReponseQuestionRepository rqr;

    @Autowired
    private EvaluationService es;

    @Autowired
    private EtudiantService ets;

    @PreAuthorize("hasRole('ETU')")
    @GetMapping("testEtu")
    public String oui(){
        return "oui";
    }
    /**
         * {
         *     "idEva" : 1,
         *     "commentaire" : "oui oui",
         *     "rList":
         *          [
             *          {
             *              "id_qev" : "1",
             *              "pos" : "2"
             *          },
             *          {
             *               "id_qev" : "2",
             *               "pos" : "3"
             *          }
         *          ]
         * }
     * @param auth
     * @param req
     * @return
     */
    @PreAuthorize("hasRole('ETU')")
    @PostMapping("repondre")
    public ResponseEntity<?> repondre(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody HashMap<String, Object> req){
        try {
            int idEva = Integer.parseInt(""+req.get("idEva"));

            if(ets.isDejaSoumis(ttip.getEtudFromToken(auth), idEva)) throw new ErrorReponseExists("L'etudiant a deja soumis une reponse");
            if(es.isClosedRep(idEva)) throw new ErrorEvaluationNoOuverte("Evaluation non mise a disposition");

            ReponseEvaluation re = new ReponseEvaluation();
            re.setNoEtudiant(ttip.getEtudFromToken(auth));
            re.setIdEvaluation(es.findById(idEva));
            if(req.get("commentaire") != null){
                re.setCommentaire(""+req.get("commentaire"));
            }
            re = rer.save(re);

            List<HashMap<String,String>> rlist = (List<HashMap<String, String>>) req.get("rList");

            if(!rlist.isEmpty()){
                for (HashMap<String, String> l : rlist) {
                    ReponseQuestion temp = new ReponseQuestion();
                    temp.setIdReponseEvaluation(re);
                    temp.setIdQuestionEvaluation(qer.findById(Integer.parseInt(l.get("id_qev"))).get());
                    temp.setPositionnement(Long.parseLong(l.get("pos")));
                    ReponseQuestionId tempid = new ReponseQuestionId();
                    tempid.setIdQuestionEvaluation(temp.getIdQuestionEvaluation().getId());
                    tempid.setIdReponseEvaluation(re.getId());
                    if(rqr.findById(tempid).isPresent()) throw new ErrorDuplicateReponse("Reponse duplique  ");
                    temp.setId(tempid);
                    rqr.save(temp);
                }
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Reponse sauvegarde avec succes");
            return ResponseEntity.ok(response);
        }
        catch(ErrorDuplicateReponse e){
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        catch(ErrorReponseExists e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        catch(Exception e){
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An internal error occurred");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @PreAuthorize("hasRole('ETU')")
    @GetMapping("consulterReponses/{id}")
    public ResponseEntity<?> consulter(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable("id") Integer idRep){
        try{
            ConsulterReponseDTO ret = new ConsulterReponseDTO();
            ReponseEvaluation re = rer.findById(idRep).get();
            ret.setEva(re.getIdEvaluation());
            ret.setCommentaireEvaluation(re.getCommentaire());
            ret.setQuestions(ets.getReponseQuestions(re));
            return ResponseEntity.ok(ret);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        //return ResponseEntity.ok("ouui");
    }
}
