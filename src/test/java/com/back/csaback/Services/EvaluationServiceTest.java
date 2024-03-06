package com.back.csaback.Services;

import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Models.RubriqueEvaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EvaluationServiceTest {

    @Mock
    private EvaluationRepository evaluationRepository;



    @Mock
    private EvaRubRepository evaRubRepository;

    @Mock
    private RubriqueService rubriqueService;

    @InjectMocks
    private EvaluationService evaluationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        List<Evaluation> evaluations = new ArrayList<>();
        when(evaluationRepository.findAll()).thenReturn(evaluations);

        List<Evaluation> result = evaluationService.getAll();

        assertNotNull(result);
        assertEquals(evaluations, result);
    }

    @Test
    void testGetEvaRub() {
        Evaluation evaluation = new Evaluation();
        List<RubriqueEvaluation> rubriqueEvaluations = new ArrayList<>();
        when(evaRubRepository.findAllByIdEvaluation(evaluation)).thenReturn(rubriqueEvaluations);

        List<Rubrique> result = evaluationService.getEvaRub(evaluation);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testConsulterEvaluation() {
        Evaluation evaluation = new Evaluation();
        List<Rubrique> rubriques = new ArrayList<>();
        when(evaluationService.getEvaRub(evaluation)).thenReturn(rubriques);
        when(rubriqueService.getRubQuest(any())).thenReturn(new ArrayList<>());

        EvaluationDetails result = evaluationService.ConsulterEvaluation(evaluation);

        assertNotNull(result);
        assertEquals(evaluation, result.getEvaluation());
        assertTrue(result.getRubriques().isEmpty());
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Evaluation evaluation = new Evaluation();
        when(evaluationRepository.findById(id)).thenReturn(Optional.of(evaluation));

        Evaluation result = evaluationService.findById(id).orElse(null);

        assertNotNull(result);
        assertEquals(evaluation, result);
    }
}