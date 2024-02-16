package com.back.csaback.Services;

import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubriqueEvaluationService {
    @Autowired
    private RubriqueEvaluationRepository rubriqueEvaluationRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RubriqueEvaluation> findByRubrique(Rubrique rubrique){
        return rubriqueEvaluationRepository.findAllByIdRubrique(rubrique);
    }
}
