package com.back.csaback.Controllers;

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

import java.util.HashMap;
import java.util.List;

@RestController
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
            int idEva = (int) (req.get("idEva"));

            if(ets.isDejaSoumis(ttip.getEtudFromToken(auth), idEva)) throw new ErrorReponseExists("L'etudiant a deja soumis une reponse");
            if(es.isClosedRep(idEva)) throw new ErrorEvaluationNoOuverte("Evaluation non mise a disposition");

            ReponseEvaluation re = new ReponseEvaluation();
            re.setNoEtudiant(ttip.getEtudFromToken(auth));
            re.setIdEvaluation(es.findById(idEva));
            if(req.get("commentaire") != null){
                re.setCommentaire(""+req.get("commentaire"));
            }
            re = rer.save(re);

            for (HashMap<String, String> l : (List<HashMap<String, String>>) req.get("rList")) {
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
            return ResponseEntity.ok("Reponse sauvegarde avec succes");
        }
        catch(ErrorDuplicateReponse e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(ErrorReponseExists e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ETU')")
    @GetMapping("consulterReponses/{id}")
    public ResponseEntity<?> consulter(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable("id") Integer idEva){
        try{
            ReponseEvaluation re = ets.getReponseEvaluation(ttip.getEtudFromToken(auth),idEva);
            return ResponseEntity.ok(ets.getReponseQuestions(re));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
