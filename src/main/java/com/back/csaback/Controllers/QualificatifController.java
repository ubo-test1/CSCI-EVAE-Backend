//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Controllers;

import com.back.csaback.DTO.QualificatifAssociated;
import com.back.csaback.Exceptions.ErrorQualificatifAssociated;
import com.back.csaback.Exceptions.QualificatifExistException;
import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Services.QualificatifService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/qualificatifs"})
public class QualificatifController {
    private final QualificatifService qualificatifService;

    @Autowired
    public QualificatifController(QualificatifService qualificatifService) {
        this.qualificatifService = qualificatifService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<QualificatifAssociated>> getAllQualificatifs() {
        List<QualificatifAssociated> qualificatifs= qualificatifService.GetAllQualificatifs();
        return  ResponseEntity.ok(qualificatifs);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQualificatif(@RequestBody Qualificatif qualificatif) {
        String minimal = qualificatif.getMinimal();
        String maximal = qualificatif.getMaximal();

        if (qualificatifService.isQualificatifExists(minimal, maximal)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Couple qualificatif déjà existant !");
        }
        Qualificatif createdQualificatif = this.qualificatifService.createQualificatif(qualificatif);
        return ResponseEntity.status(HttpStatus.CREATED).body("Couple qualificatif créé avec succès !");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQualificatif(@PathVariable("id") Long idQualificatif) {
        try {
            this.qualificatifService.deleteQualificatif(idQualificatif);
            return ResponseEntity.ok().build();
        } catch (ErrorQualificatifAssociated ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }



    @GetMapping({"/find/{id}"})
    public ResponseEntity<Qualificatif> getQualificatifById(@PathVariable("id") Long idQualificatif) {
        try{
            Qualificatif qualificatif = this.qualificatifService.findQualificationById(idQualificatif);
            return ResponseEntity.ok(qualificatif);
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> updateQualificatif(@PathVariable("id") Long idQualificatif, @RequestBody Qualificatif qualificatif , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation: " + bindingResult.getAllErrors());
        }else {
            try {
                Qualificatif updatedQualificatif= qualificatifService.updateQualificatif(idQualificatif, qualificatif);
                return ResponseEntity.ok(updatedQualificatif);
            } catch (ErrorQualificatifAssociated ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }catch (EntityNotFoundException ex) {
                return ResponseEntity.notFound().build();
            }catch (QualificatifExistException ex) {
                return ResponseEntity.badRequest().body("Un autre couple qualificatif avec les mêmes valeurs minimal et maximal existe déjà.");


    }}
}}
