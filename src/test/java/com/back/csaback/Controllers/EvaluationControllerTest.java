package com.back.csaback.Controllers;

import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.Promotion;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Services.ElementConstitutifService;
import com.back.csaback.Services.EnseignantService;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.PromotionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EvaluationControllerTest {

    @Mock
    private EvaluationService evaluationService;

    @Mock
    private EnseignantService enseignantService;

    @Mock
    private ElementConstitutifService elementConstitutifService;

    @Mock
    private PromotionService promotionService;

    @InjectMocks
    private EvaluationController evaluationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateEvae() {
        HashMap<String, Object> request = new HashMap<>();
        request.put("noEnseignant", 1);
        request.put("codeFormation", "ABC");
        request.put("codeUe", "UE1");
        request.put("codeEc", "EC1");
        request.put("anneeUniversitaire", "2023-2024");
        request.put("id", 1);
        request.put("noEvaluation", 1);
        request.put("designation", "Test Evaluation");
        request.put("etat", "Active");
        request.put("periode", "Spring");
        request.put("debutReponse", LocalDate.now().toString());
        request.put("finReponse", LocalDate.now().plusDays(7).toString());

        Enseignant enseignant = new Enseignant();
        when(enseignantService.findById(1)).thenReturn(Optional.of(enseignant));

        ElementConstitutif elementConstitutif = new ElementConstitutif();
        when(elementConstitutifService.findById(any())).thenReturn(Optional.of(elementConstitutif));

        Promotion promotion = new Promotion();
        when(promotionService.findById(any())).thenReturn(Optional.of(promotion));

        Evaluation evaluation = new Evaluation();
        when(evaluationService.createEvaluation(any())).thenReturn(evaluation);
       // ResponseEntity<?> responseEntity = evaluationController.save(request);
        //assertEquals(ResponseEntity.ok(evaluation), responseEntity);
    }

    @Test
    public void testGetAll() {
        // Mock data
        List<EvaDTO> expected = new ArrayList<>();
        when(evaluationService.getAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<EvaDTO>> responseEntity = evaluationController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void testConsulterInfo() {
        // Mock data
        Integer id = 1;
        Evaluation evaluation = new Evaluation();
        EvaluationDetails expected = new EvaluationDetails();
        when(evaluationService.findByIdOpt(anyInt())).thenReturn(Optional.of(evaluation));
        when(evaluationService.ConsulterEvaluation(any())).thenReturn(expected);

        ResponseEntity<EvaluationDetails> responseEntity = evaluationController.consulterInfo(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());
    }

/*    @Test
    public void testUpdateEvaluation() throws Exception {
        // Mock data
        Evaluation evaluation = new Evaluation();
        when(evaluationService.updateEvaluation(any())).thenReturn(evaluation);

        ResponseEntity<?> responseEntity = evaluationController.updateEvaluation(evaluation);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(evaluation, responseEntity.getBody());
    }*/
    @Test
    public void testDeleteEvaluation() {
        // Mock data
        Integer noEvaluation = 1;
        Evaluation evaluation = new Evaluation();
        when(evaluationService.findByIdOpt(anyInt())).thenReturn(Optional.of(evaluation));

        ResponseEntity<?> responseEntity = evaluationController.deleteEvaluation(noEvaluation);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("L'évaluation a été supprimée avec succès.", responseEntity.getBody());
    }
}