package com.back.csaback.Controllers;

import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Services.RubriqueService;
import com.back.csaback.Services.Tooltip;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rub")
public class RubriqueController {
    @Autowired
    private RubriqueService rs;
    @Autowired
    private Tooltip ttip;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("createStd")
    public ResponseEntity<Rubrique> createStd(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,@Valid @RequestBody Rubrique r){
        try{
            Rubrique ru = new Rubrique();
            ru.setType(r.getType());
            ru.setOrdre(r.getOrdre());
            ru.setDesignation(r.getDesignation());
            ru.setNoEnseignant(ttip.getUserFromToken(auth));
            return ResponseEntity.ok(rs.createRubStd(ru));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("updateStd")
    public ResponseEntity<Rubrique> updateStd(@RequestBody Rubrique r){
        try{
            return ResponseEntity.ok(rs.updateRub(r));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("consulterStd/{id}")
    public ResponseEntity<Rubrique> consulterStd(@RequestBody Long id){
        try{
            return ResponseEntity.ok(rs.consulter(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/allStd")
    public ResponseEntity<List<RubriqueAssociated>> getAll() {
        List<RubriqueAssociated> questions= rs.getAllStd();
        return  ResponseEntity.ok(questions);
    }
    @DeleteMapping("/deleteStd/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
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
    @GetMapping("/test")
    public ResponseEntity<String> gettest() {
        return  ResponseEntity.ok("test bien passé");
    }
}
