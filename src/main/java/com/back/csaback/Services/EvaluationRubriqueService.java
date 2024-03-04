package com.back.csaback.Services;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationRubriqueService {

    @Autowired
    private RubriqueEvaluationRepository rubriqueEvaluationRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private RubriqueService rubriqueService;


    public EvaluationRubriqueService(RubriqueEvaluationRepository rubriqueEvaluationRepository) {
        this.rubriqueEvaluationRepository = rubriqueEvaluationRepository;
    }

    public RubriqueEvaluation attachRubriqueToEval(Integer evaluationId, Integer rubriqueId, short ordre, String designation){

        RubriqueEvaluation rubriqueEvaluation = new RubriqueEvaluation();
        Evaluation eval = evaluationService.findById(evaluationId);
        Rubrique rubrique = rubriqueService.findById(rubriqueId);

        if(rubrique ==null || eval == null) throw new NullPointerException();

        rubriqueEvaluation.setIdEvaluation(eval);
        rubriqueEvaluation.setIdRubrique(rubrique);
        rubriqueEvaluation.setOrdre(ordre);
        rubriqueEvaluation.setDesignation(designation);

        return rubriqueEvaluationRepository.save(rubriqueEvaluation);
    }
    public RubriqueEvaluation attachRubriqueToEval(Integer evaluationId, Integer rubriqueId, short ordre) {
        return attachRubriqueToEval(evaluationId,rubriqueId, ordre,null);
    }
}
