package com.back.csaback.Controllers;


import com.back.csaback.DTO.QuestionAssociated;
import com.back.csaback.Exceptions.ErrorQuestionAssociated;
import com.back.csaback.Models.Question;
import com.back.csaback.Services.QuestionStandardService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Contrôleur REST responsable de la gestion des requêtes liées aux questions standard.
 * Ce contrôleur offre des endpoints pour la création, la récupération, la mise à jour et la suppression de questions,
 * ainsi que des méthodes de gestion des exceptions spécifiques.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2023
 */

@RestController
@RequestMapping("/eva/qus")
public class QuestionStandardController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionStandardController.class);

    @Autowired
    QuestionStandardService questionStandardService;
    @PostMapping("/create")
    public ResponseEntity<String> save(@Valid @RequestBody Question newQuestion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation: " + bindingResult.getAllErrors());
        }else {
             questionStandardService.save(newQuestion);
        return ResponseEntity.status(201).body("question créé avec succès");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<QuestionAssociated>> getAll() {
        List<QuestionAssociated> questions= questionStandardService.getAll();
            return  ResponseEntity.ok(questions);
    }
    @GetMapping("/test")
    public ResponseEntity<String> gettest() {
        return  ResponseEntity.ok("test bien passé");
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Question> getById(@PathVariable Long id) {
        try {
            Question question = questionStandardService.findById(id);
            return ResponseEntity.ok(question);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

   @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            questionStandardService.delete(id);
            return ResponseEntity.ok("question supprimée");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()) ;
        } catch (ErrorQuestionAssociated ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> update( @Valid @RequestBody Question question, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Erreur de validation: " + bindingResult.getAllErrors());
        }else {
        try {
            Question updatedQuestion = questionStandardService.update(question);
            return ResponseEntity.ok(updatedQuestion);
        } catch (ErrorQuestionAssociated ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }
    }

}
