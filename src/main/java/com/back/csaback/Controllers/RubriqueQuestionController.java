package com.back.csaback.Controllers;


import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Models.RubriqueQuestionId;
import com.back.csaback.Services.QuestionService;
import com.back.csaback.Services.RubriqueEvaluationService;
import com.back.csaback.Services.RubriqueQuestionService;
import com.back.csaback.Services.RubriqueService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/rubrique-questions")
public class RubriqueQuestionController {

    @Autowired
    private RubriqueQuestionService rubriqueQuestionService;

    @Autowired
    private RubriqueService rubriqueService;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private RubriqueEvaluationService rubriqueEvaluationService;

    @PostMapping("/create")
    public ResponseEntity<?> createRubriqueQuestion(@RequestBody HashMap<String,String> req /*Long rubriqueId, @RequestBody Long questionId, @RequestBody BigDecimal ordre*/) {


        try {

            Long rubriqueId = Long.parseLong(req.get("idRubrique"));
            Long questionId = Long.parseLong(req.get("idQuestion"));
            Long ordre = Long.parseLong(req.get("ordre"));
            System.out.println("q id: "+questionId);
            Rubrique rubrique= rubriqueService.getRubrique(rubriqueId).orElse(null);
            if (rubrique == null) {
                throw new IllegalArgumentException("La rubrique spécifiée n'existe pas.");
            }
            Question question = questionService.getQuestion(questionId).orElse(null);
            // Check if the question exists
            if (question == null) {
                throw new IllegalArgumentException("La question spécifiée n'existe pas.");
            }

            RubriqueQuestionId rubriqueQuestionId= new RubriqueQuestionId(rubriqueId,questionId);
            RubriqueQuestion rubriqueQuestion= new RubriqueQuestion();
            rubriqueQuestion.setIdQuestion(question);
            rubriqueQuestion.setIdRubrique(rubrique);
            rubriqueQuestion.setOrdre(ordre);
            rubriqueQuestion.setId(rubriqueQuestionId);


            // Check if an instance with the same idRubrique and idQuestion already exists
            if (rubriqueQuestionService.existsByRubriqueAndQuestion(rubriqueQuestion.getIdRubrique(), rubriqueQuestion.getIdQuestion())) {
                throw new IllegalArgumentException("Cette question existe déjà pour cette rubrique.");
            }
            // If no existing instance, save the new RubriqueQuestion instance
            RubriqueQuestion savedRubriqueQuestion = rubriqueQuestionService.saveRubriqueQuestion(rubriqueQuestion);
            return ResponseEntity.ok(savedRubriqueQuestion);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getall/{rubriqueId}")
    public List<RubriqueQuestion> getAllRubriqueQuestionsByRubriqueId(@PathVariable Long rubriqueId) {
        List<RubriqueQuestion> rubriqueQuestions = rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubriqueId);

        if (rubriqueQuestions.isEmpty()) {
            throw new RuntimeException("Pas de questions dans cette rubrique");
        }
        return rubriqueQuestions;
    }




    @Transactional
    @DeleteMapping("/delete/{rubriqueId}")
    public void deleteByRubrique(@PathVariable Long rubriqueId) {
        Rubrique rubrique = rubriqueService.getRubrique(rubriqueId).orElse(null);
        if (rubrique == null) {
            throw new IllegalArgumentException("La rubrique spécifiée n'existe pas.");
        }

        if (!rubriqueEvaluationService.findByRubrique(rubrique).isEmpty()) {
            throw new IllegalArgumentException("La rubrique est utilisée dans une évaluation.");
        }

        rubriqueQuestionService.deleteAllByIdRubrique(rubrique);
    }
    @DeleteMapping("/deletebyquestion")
    public void deleteById(@PathVariable RubriqueQuestionId rubriqueQuestionId){
        Long rubriqueid=rubriqueQuestionId.getIdRubrique();
        Rubrique rubrique=rubriqueService.getRubrique(rubriqueid).orElse(null);
        if (!rubriqueEvaluationService.findByRubrique(rubrique).isEmpty()) {
            throw new IllegalArgumentException("La rubrique est utilisée dans une évaluation.");
        }
        rubriqueQuestionService.deleteById(rubriqueQuestionId);

    }
    @PutMapping("/update/{rubriqueId}")
    public void Update(@PathVariable Long rubriqueId, @RequestBody RubriqueQuestion newRubriqueQuestion) {
        // Find the RubriqueQuestion by RubriqueID
        List<RubriqueQuestion> existingRubriqueQuestions = rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubriqueId);

        // Check if the new questionId is already associated with another RubriqueID
        for (RubriqueQuestion existingRubriqueQuestion : existingRubriqueQuestions) {
            if (existingRubriqueQuestion.getIdQuestion().equals(newRubriqueQuestion.getIdQuestion())) {
                throw new IllegalArgumentException("The new questionId is already associated with another RubriqueID.");
            }
        }

        // Update the ordre and questionId
        for (RubriqueQuestion existingRubriqueQuestion : existingRubriqueQuestions) {
            existingRubriqueQuestion.setOrdre(newRubriqueQuestion.getOrdre());
            existingRubriqueQuestion.setIdQuestion(newRubriqueQuestion.getIdQuestion());

            rubriqueQuestionService.updateByOrdre(existingRubriqueQuestion);
        }
    }


}


