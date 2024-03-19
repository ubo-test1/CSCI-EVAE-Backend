package com.back.csaback.Controllers;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Services.QuestionEvaluationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Classe de test pour le contrôleur {@link QuestionEvaluationController}.
 * Cette classe teste les différentes méthodes du contrôleur {@link QuestionEvaluationController}.
 * Elle utilise Mockito pour simuler le comportement des services et des dépendances.
 * Les tests vérifient les cas de succès ainsi que les cas d'erreur, tels que les exceptions lancées.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 05/03/2024
 */

@WebMvcTest(QuestionEvaluationController.class)
@AutoConfigureMockMvc(addFilters = false)
class QuestionEvaluationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionEvaluationService questionEvaluationService;
    @InjectMocks
    private QuestionEvaluationController questionEvaluationController;

    @Test
    public void testSave() throws Exception {
        // Créer une instance de QuestionEvaluation pour la requête POST
        QuestionEvaluation questionEvaluation = new QuestionEvaluation();
        questionEvaluation.setId(1);
        questionEvaluation.setIdQuestion(new Question());
        questionEvaluation.setIdRubriqueEvaluation(new RubriqueEvaluation());
        questionEvaluation.setOrdre((short) 1);

        // Simuler le service pour le cas où il enregistre avec succès
        when(questionEvaluationService.save(any(QuestionEvaluation.class))).thenReturn(questionEvaluation);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonQuestion = objectMapper.writeValueAsString(questionEvaluation);
        // Simuler une requête POST avec le JSON de la nouvelle question
        mockMvc.perform(MockMvcRequestBuilders.post("/eva/quv/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonQuestion))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void testDeleteById_Success() throws Exception {
        // Arrange
        int idToDelete = 1;
        doNothing().when(questionEvaluationService).delete(eq(idToDelete));

        // Act
        ResultActions result = mockMvc.perform(delete("/eva/quv/delete/{id}", idToDelete));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    void testDeleteById_EntityNotFound() throws Exception {
        // Arrange
        int idToDelete = 1;
        doThrow(new EntityNotFoundException("Question not found")).when(questionEvaluationService).delete(eq(idToDelete));

        // Act
        ResultActions result = mockMvc.perform(delete("/eva/quv/delete/{id}", idToDelete));

        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void testDeleteById_IllegalArgumentException() throws Exception {
        // Arrange
        int idToDelete = 1;
        doThrow(new IllegalArgumentException("Invalid argument")).when(questionEvaluationService).delete(eq(idToDelete));

        // Act
        ResultActions result = mockMvc.perform(delete("/eva/quv/delete/{id}", idToDelete));

        // Assert
        result.andExpect(status().isBadRequest());
    }
    @Test
    void testGetAllByIdRubrique_Success() throws Exception {
        // Arrange
        int id = 1;
        List<QuestionEvaluation> questionEvaluations = Collections.emptyList();
        doReturn(questionEvaluations).when(questionEvaluationService).findByIdRubrique(eq(id));
        // Act
        ResultActions result = mockMvc.perform(get("/eva/quv/findByIdRubrique/{id}", id));
        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    void testGetAllByIdRubrique_EntityNotFound() throws Exception {
        // Arrange
        int id = 1;
        String errorMessage = "Entity not found";
        doThrow(new EntityNotFoundException(errorMessage)).when(questionEvaluationService).findByIdRubrique(eq(id));

        // Act
        ResultActions result = mockMvc.perform(get("/eva/quv/findByIdRubrique/{id}", id));

        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void testOrdonner_Success() throws Exception {
        // Arrange
        List<QuestionEvaluation> questionEvaluations = Collections.emptyList();
        when(questionEvaluationService.findById(any(Integer.class))).thenReturn(new QuestionEvaluation());
        doReturn(new QuestionEvaluation()).when(questionEvaluationService).update(any(QuestionEvaluation.class));

        // Act
        ResultActions result = mockMvc.perform(put("/eva/quv/ordonner")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":1,\"ordre\":2},{\"id\":2,\"ordre\":1}]"));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    void testOrdonner_EntityNotFound() throws Exception {
        // Arrange
        String errorMessage = "Entity not found";
        List<QuestionEvaluation> questionEvaluations = Collections.singletonList(new QuestionEvaluation());
        when(questionEvaluationService.findById(any(Integer.class))).thenThrow(new EntityNotFoundException(errorMessage));

        // Act
        ResultActions result = mockMvc.perform(put("/eva/quv/ordonner")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":1,\"ordre\":2},{\"id\":2,\"ordre\":1}]"));

        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void testGetAll() throws Exception {
        // Mocking the service to return a list of questions
        List<QuestionEvaluation> mockQuestions = Arrays.asList(new QuestionEvaluation(), new QuestionEvaluation());
        when(questionEvaluationService.getAll()).thenReturn(mockQuestions);

        // Performing the GET request and verifying the response
        mockMvc.perform(MockMvcRequestBuilders.get("/eva/quv/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockQuestions.size()));
    }



}