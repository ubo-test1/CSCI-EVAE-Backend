package com.back.csaback.Controllers;

import com.back.csaback.DTO.RubQRequest;
import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestionId;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Services.RubriqueQuestionService;
import com.back.csaback.Services.RubriqueService;
import com.back.csaback.Services.Tooltip;
import jakarta.websocket.server.PathParam;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

import java.util.List;

@RestController
@RequestMapping("rub")
@CrossOrigin
public class RubriqueController {

    @Autowired
    private RubriqueService rs;

    @Autowired
    private RubriqueRepository rr;

    @Autowired
    private Tooltip ttip;

    @Autowired
    private QuestionRepository qr;

    @Autowired
    private RubriqueQuestionService rqs;

    /**
     * @author Saad Hadiche
     * @param auth
     * @param r
     * Request:
     * {
     *     "designation" : "oui"
     * }
     * @return
     */
    @PreAuthorize("hasRole('ADM')")
    @PostMapping("create")
    public ResponseEntity<?> createStd(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody Rubrique r){
        try{
            Rubrique ru = new Rubrique();
            ru.setDesignation(r.getDesignation());
            if(r.getNoEnseignant() == null){
                if(!ttip.getRoleFromToken(auth).equals("ADM")) ru.setNoEnseignant(ttip.getUserFromToken(auth));
            }
            Long maxOrdre = rr.findMaxOrdre();
            ru.setOrdre(maxOrdre + 1);
            if(ttip.getRoleFromToken(auth).equals("ADM")){
                return ResponseEntity.ok(rs.createRubStd(ru));
            }
            return ResponseEntity.ok(rs.createRubPers(ru));
        }
        catch(IllegalArgumentException ee){
            ee.printStackTrace();
            return ResponseEntity.badRequest().body(ee.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * {
     *     "id": 13,
     *     "type": "RBP",
     *     "noEnseignant": 1,
     *     "designation": "AAAAAAAA",
     *     "ordre": 11
     * }
     * @return
     */
    @PreAuthorize("hasRole('ADM')")
    @PostMapping("update")
    public ResponseEntity<?> updateStd(@RequestBody Rubrique r){
        try{

            return ResponseEntity.ok(rs.updateRub(r));
        }catch(IllegalArgumentException | IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()) ;
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //@PreAuthorize("hasRole('ADM')")
    @GetMapping("consulter/{id}")
    public ResponseEntity<?> consulterStd(@PathVariable("id") Integer id){
        try{
            Rubrique r = rs.findById(id);
            if(rs.isCompose(r)){
                return ResponseEntity.ok(rs.consulterRubriqueComp(r));
            }
            return ResponseEntity.ok(rs.consulter(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PreAuthorize("hasRole('ADM')")
    @GetMapping("/allStd")
    public ResponseEntity<List<RubriqueAssociated>> getAll() {
        List<RubriqueAssociated> questions= rs.getAllStd();
        return  ResponseEntity.ok(questions);
    }
    @PreAuthorize("hasRole('ADM')")
    @DeleteMapping("/deleteStd/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        try {
            rs.delete(id);
            return ResponseEntity.ok("rubrique supprimée");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()) ;
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * {
     *     "rubriqueId" : 49,
     *     "qList":[1,2,3,4,5]
     * }
     * @param req
     * @return
     */
    @PreAuthorize("hasRole('ADM')")
    @PostMapping("/assignQuestion")
    public ResponseEntity<?> addQ(@RequestBody HashMap<String,Object> req){
        try{
            RubQRequest test = new RubQRequest();
            test.setRubriqueId((Integer)req.get("rubriqueId"));
            test.setQList((List<Integer>)req.get("qList"));
            rs.assignQList(test);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erreur");
        }
    }

    /**
     * {
     *     "rubriqueId" : 49,
     *     "qList":[1,2,3,4,5]
     * }
     * @param req
     * @return
     */
    @PreAuthorize("hasRole('ADM')")
    @PostMapping ("/deletebyquestion")
    public ResponseEntity<?> deleteById(@RequestBody HashMap<String,Object> req){
        try{
            Integer rubId = (Integer) req.get("rubriqueId");
            for(Integer i : (List<Integer>) req.get("qList")){
                RubriqueQuestionId tmp = new RubriqueQuestionId();
                rqs.deattachQuestion(rubId,i);
            }
            return ResponseEntity.ok("Questions supprime avec succes");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> gettest() {
        return  ResponseEntity.ok("test bien passé");
    }

    @GetMapping("testQ")
    public List<Question> test(){
        return qr.findAll();
    }
}
