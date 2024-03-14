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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/qualificatif"})
@CrossOrigin
public class QualificatifController {

    @Autowired
    private QualificatifService qualificatifService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<QualificatifAssociated>> getAllQualificatifs() {
        List<QualificatifAssociated> qualificatifs = qualificatifService.GetAllQualificatifs();
        return  ResponseEntity.ok(qualificatifs);
    }

    /**
     * {
     *     "maximal":"test1",
     *     "minimal":"test1"
     * }
     * @param qualificatif
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<?> createQualificatif(@RequestBody Qualificatif qualificatif) {
        try{
            String minimal = qualificatif.getMinimal();
            String maximal = qualificatif.getMaximal();

            if (qualificatifService.isQualificatifExists(minimal, maximal)) {
                return ResponseEntity.badRequest().body("Couple qualificatif déjà existant !");
            }
            Qualificatif createdQualificatif = this.qualificatifService.createQualificatif(qualificatif);
            return ResponseEntity.ok(createdQualificatif);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<?> deleteQualificatif(@PathVariable("id") Integer idQualificatif) {
        try {
            this.qualificatifService.deleteQualificatif(idQualificatif);
            return ResponseEntity.ok("Le couple qualificatif a ete supprime");
        } catch (ErrorQualificatifAssociated ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }



    @GetMapping({"/find/{id}"})
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Qualificatif> getQualificatifById(@PathVariable("id") Integer idQualificatif) {
        try{
            Qualificatif qualificatif = this.qualificatifService.findQualificationById(idQualificatif);
            return ResponseEntity.ok(qualificatif);
        }
        catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {
     *     "id":15,
     *     "maximal":"non",
     *     "minimal":"non"
     * }
     * @param qualificatif
     * @return
     */
    @PutMapping({"update"})
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<?> updateQualificatif(@RequestBody Qualificatif qualificatif , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation: " + bindingResult.getAllErrors());
        }else {
            try {
                Qualificatif updatedQualificatif= qualificatifService.updateQualificatif(qualificatif);
                return ResponseEntity.ok(updatedQualificatif);
            } catch (ErrorQualificatifAssociated ex) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            } catch (IllegalArgumentException ex) {

                return ResponseEntity.badRequest().body(ex.getMessage());
            }catch (EntityNotFoundException ex) {

                return ResponseEntity.notFound().build();
            }catch (QualificatifExistException ex) {

                return ResponseEntity.badRequest().body("Un autre couple qualificatif avec les mêmes valeurs minimal et maximal existe déjà.");
            }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
    }}
}
