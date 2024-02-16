package com.back.csaback.Controllers;

import com.back.csaback.Models.Rubrique;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Services.RubriqueService;
import com.back.csaback.Services.Tooltip;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("rub")
public class RubriqueController {

    @Autowired
    private RubriqueService rs;

    @Autowired
    private RubriqueRepository rr;

    @Autowired
    private Tooltip ttip;

    /**
     *
     * @param auth
     * @param r
     * {
     *     "type" : "RBS",
     *     "designation" : "oui"
     * }
     * @return
     */
    @PreAuthorize("hasRole('ADM')")
    @PostMapping("createStd")
    public ResponseEntity<?> createStd(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody Rubrique r){
        try{
            Rubrique ru = new Rubrique();
            if(r.getType().equals("RBP") || r.getType().equals("RBS")) ru.setType(r.getType());
            else throw new IllegalArgumentException("Type doit etre RBS ou RBP");
            ru.setDesignation(r.getDesignation());
            if(r.getNoEnseignant() != null) ru.setNoEnseignant(ttip.getUserFromToken(auth));
            Long maxOrdre = rr.findMaxOrdre();
            ru.setOrdre(maxOrdre + 1);
            return ResponseEntity.ok(rs.createRubStd(ru));
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
     *     "noEnseignant": null,
     *     "designation": "AAAAAAAA",
     *     "ordre": 11
     * }
     * @param r
     * @return
     */
    @PreAuthorize("hasRole('ADM')")
    @PostMapping("updateStd")
    public ResponseEntity<Rubrique> updateStd(@RequestBody Rubrique r){
        try{
            Rubrique q = rr.findById(Long.parseLong(""+r.getId())).get();
            q.setType(r.getType());
            q.setDesignation(r.getDesignation());
            q.setOrdre(r.getOrdre());
            return ResponseEntity.ok(rs.updateRub(q));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ADM')")
    @GetMapping("consulterStd/{id}")
    public ResponseEntity<Rubrique> consulterStd(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(rs.consulter(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
