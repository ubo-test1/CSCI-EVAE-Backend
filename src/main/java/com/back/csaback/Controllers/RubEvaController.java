package com.back.csaback.Controllers;

import com.back.csaback.DTO.RubriqueEvaDetails;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rubEva")
@CrossOrigin
public class RubEvaController {

    @Autowired
    private EvaRubRepository err;

    @Autowired
    private QuestionEvaluationRepository qer;

    @PreAuthorize("hasRole('ADM') || hasRole('ENS')")
    @GetMapping("consulter/{id}")
    public ResponseEntity<?> consulterStd(@PathVariable("id") Integer id){
        try{
            RubriqueEvaluation re = err.findById(id).get();
            List<QuestionEvaluation> lqe = qer.findAllByidRubriqueEvaluation(re);
            if(lqe.isEmpty()){
                return ResponseEntity.ok(re);
            }
            RubriqueEvaDetails ret = new RubriqueEvaDetails();
            ret.setRubrique(re);
            ret.setQuestions(lqe);
            return ResponseEntity.ok(ret);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
