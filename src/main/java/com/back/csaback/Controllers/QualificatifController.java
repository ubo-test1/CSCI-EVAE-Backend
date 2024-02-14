//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Controllers;

import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Services.QualificatifService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Qualificatif> getAllQualificatifs() {
        return this.qualificatifService.GetAllQualificatifs();
    }

    @PostMapping
    public ResponseEntity<Qualificatif> createQualificatif(@RequestBody Qualificatif qualificatif) {
        Qualificatif createdQualificatif = this.qualificatifService.createQualificatif(qualificatif);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQualificatif);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteQualificatif(@PathVariable("id") Long idQualificatif) {
        this.qualificatifService.deleteQualificatif(idQualificatif);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Qualificatif> getQualificatifById(@PathVariable("id") Long idQualificatif) {
        Qualificatif qualificatif = this.qualificatifService.findQualificationById(idQualificatif);
        return ResponseEntity.ok().body(qualificatif);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Qualificatif> updateQualificatif(@PathVariable("id") Long idQualificatif, @RequestBody Qualificatif qualificatif) {
        if (!idQualificatif.equals(qualificatif.getIdQualificatif())) {
            return ResponseEntity.badRequest().build();
        } else {
            Qualificatif updatedQualificatif = this.qualificatifService.updateQualificatifById(qualificatif);
            return ResponseEntity.ok().body(updatedQualificatif);
        }
    }
}
