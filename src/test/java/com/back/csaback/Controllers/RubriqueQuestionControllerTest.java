package com.back.csaback.Controllers;

import com.back.csaback.Controllers.RubriqueQuestionController;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Services.QuestionService;
import com.back.csaback.Services.RubriqueEvaluationService;
import com.back.csaback.Services.RubriqueQuestionService;
import com.back.csaback.Services.RubriqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class RubriqueQuestionControllerTest {

    @Mock
    private RubriqueQuestionService rubriqueQuestionService;

    @Mock
    private RubriqueService rubriqueService;

    @Mock
    private QuestionService questionService;

    @Mock
    private RubriqueEvaluationService rubriqueEvaluationService;

    @InjectMocks
    private RubriqueQuestionController rubriqueQuestionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRubriqueQuestion() {
        HashMap<String, String> req = new HashMap<>();
        req.put("idRubrique", "1");
        req.put("idQuestion", "2");
        req.put("ordre", "1");

        Rubrique rubrique = new Rubrique();
        rubrique.setId(1L);

        Question question = new Question();
        question.setId(2L);

        when(rubriqueService.getRubrique(1L)).thenReturn(Optional.of(rubrique));
        when(questionService.getQuestion(2L)).thenReturn(Optional.of(question));
        when(rubriqueQuestionService.existsByRubriqueAndQuestion(rubrique, question)).thenReturn(false);

        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        when(rubriqueQuestionService.saveRubriqueQuestion(any())).thenReturn(rubriqueQuestion);

        ResponseEntity<?> response = rubriqueQuestionController.createRubriqueQuestion(req);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rubriqueQuestion, response.getBody());
    }

    @Test
    void testGetAllRubriqueQuestionsByRubriqueId() {
        Long rubriqueId = 1L;
        RubriqueQuestion rubriqueQuestion1 = new RubriqueQuestion();
        RubriqueQuestion rubriqueQuestion2 = new RubriqueQuestion();
        List<RubriqueQuestion> rubriqueQuestions = List.of(rubriqueQuestion1, rubriqueQuestion2);

        when(rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubriqueId)).thenReturn(rubriqueQuestions);

        List<RubriqueQuestion> result = rubriqueQuestionController.getAllRubriqueQuestionsByRubriqueId(rubriqueId);
        assertEquals(rubriqueQuestions, result);
    }

    @Test
    void testDeleteByRubrique() {
        Long rubriqueId = 1L;
        Rubrique rubrique = new Rubrique();
        rubrique.setId(rubriqueId);

        doNothing().when(rubriqueQuestionService).deleteAllByIdRubrique(rubrique);
        when(rubriqueService.getRubrique(rubriqueId)).thenReturn(Optional.of(rubrique));
        when(rubriqueEvaluationService.findByRubrique(rubrique)).thenReturn(List.of());

        rubriqueQuestionController.deleteByRubrique(rubriqueId);

        verify(rubriqueQuestionService, times(1)).deleteAllByIdRubrique(rubrique);
    }
    @Test
    void testUpdateRubriqueQuestion() {
        Long rubriqueId = 1L;

        // Create an example existing RubriqueQuestion
        RubriqueQuestion existingRubriqueQuestion = new RubriqueQuestion();
        existingRubriqueQuestion.setIdRubrique(new Rubrique());
        existingRubriqueQuestion.setIdQuestion(new Question());
        existingRubriqueQuestion.setOrdre(1L);

        // Mock the behavior of getAllRubriqueQuestionsByRubriqueId to return the existing RubriqueQuestion
        when(rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubriqueId)).thenReturn(Collections.singletonList(existingRubriqueQuestion));

        // Create a new RubriqueQuestion with the same questionId
        RubriqueQuestion newRubriqueQuestion = new RubriqueQuestion();
        newRubriqueQuestion.setIdQuestion(existingRubriqueQuestion.getIdQuestion());
        newRubriqueQuestion.setOrdre(2L);

        // Call the controller method
        assertThrows(IllegalArgumentException.class, () -> rubriqueQuestionController.Update(rubriqueId, newRubriqueQuestion));

        // Verify that the getAllRubriqueQuestionsByRubriqueId method is called with the correct argument
        verify(rubriqueQuestionService).getAllRubriqueQuestionsByRubriqueId(rubriqueId);

        // Verify that the updateByOrdre method is not called
        verify(rubriqueQuestionService, never()).updateByOrdre(any());
    }



    // Add more tests for other controller methods as needed
}
