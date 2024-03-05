package com.back.csaback.Services;

import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe {@link QuestionEvaluationService}.
 * Cette classe utilise Mockito pour simuler le comportement des dépendances de {@link QuestionEvaluationService}.
 * @author EL KRISSI Achraf
 * @version V1
 * @since 05/02/2024
 */
class QuestionEvaluationServiceTest {
    @Mock
    QuestionEvaluationRepository mockQuestionEvaluationRepository;
    @Mock
    RubriqueEvaluationRepository mockRubriqueEvaluationRepository;

    @InjectMocks
    QuestionEvaluationService questionEvaluationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void save() {
        // Given
        QuestionEvaluation newQuestionEvaluation = new QuestionEvaluation();
        when(mockQuestionEvaluationRepository.existsByIdQuestionAndIdRubriqueEvaluation(any(com.back.csaback.Models.Question.class), any(com.back.csaback.Models.RubriqueEvaluation.class))).thenReturn(false);
        when(mockQuestionEvaluationRepository.save(newQuestionEvaluation)).thenReturn(newQuestionEvaluation);

        // When
        QuestionEvaluation savedQuestionEvaluation = questionEvaluationService.save(newQuestionEvaluation);

        // Then
        assertNotNull(savedQuestionEvaluation);
        assertEquals(newQuestionEvaluation, savedQuestionEvaluation);
    }

    @Test
    void delete_ExistingQuestionEvaluation_DeletesSuccessfully() {
        // Given
        Integer questionId = 1;
        QuestionEvaluation questionToDelete = new QuestionEvaluation();
        when(mockQuestionEvaluationRepository.findById(questionId)).thenReturn(Optional.of(questionToDelete));
        // When
        questionEvaluationService.delete(questionId);

        // Then
        verify(mockQuestionEvaluationRepository).delete(questionToDelete);
    }

    @Test
    void delete_NonExistingQuestionEvaluation_ThrowsEntityNotFoundException() {
        // Given
        Integer questionId = 1;
        when(mockQuestionEvaluationRepository.findById(questionId)).thenReturn(Optional.empty());

        // Then
        assertThrows(EntityNotFoundException.class, () -> questionEvaluationService.delete(questionId));
    }

    @Test
    void testFindByIdExistingId() {
        // Arrange
        Integer id = 1;
        QuestionEvaluation expectedQuestion = new QuestionEvaluation();
        expectedQuestion.setId(id);
        when(mockQuestionEvaluationRepository.findById(id)).thenReturn(Optional.of(expectedQuestion));

        // Act
        QuestionEvaluation actualQuestion = questionEvaluationService.findById(id);

        // Assert
        assertNotNull(actualQuestion);
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void testFindByIdNonExistingId() {
        // Arrange
        Integer id = 2;
        when(mockQuestionEvaluationRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> questionEvaluationService.findById(id));
    }

    @Test
    void testGetAll() {
        // Arrange
        List<QuestionEvaluation> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new QuestionEvaluation());
        expectedQuestions.add(new QuestionEvaluation());
        expectedQuestions.add(new QuestionEvaluation());
        when(mockQuestionEvaluationRepository.findAll()).thenReturn(expectedQuestions);

        // Act
        List<QuestionEvaluation> actualQuestions = questionEvaluationService.getAll();

        // Assert
        assertNotNull(actualQuestions);
        assertEquals(expectedQuestions.size(), actualQuestions.size());

    }

    @Test
    void testFindByIdRubrique() {
        // Arrange
        int rubriqueId = 1;
        RubriqueEvaluation rubriqueEvaluation = new RubriqueEvaluation();
        when(mockRubriqueEvaluationRepository.findById(rubriqueId)).thenReturn(Optional.of(rubriqueEvaluation));

        List<QuestionEvaluation> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new QuestionEvaluation());
        expectedQuestions.add(new QuestionEvaluation());
        expectedQuestions.add(new QuestionEvaluation());
        when(mockQuestionEvaluationRepository.findAllByidRubriqueEvaluation(rubriqueEvaluation)).thenReturn(expectedQuestions);

        // Act
        List<QuestionEvaluation> actualQuestions = questionEvaluationService.findByIdRubrique(rubriqueId);

        // Assert
        assertNotNull(actualQuestions);
        assertEquals(expectedQuestions.size(), actualQuestions.size());

    }
    @Test
    void testUpdate() {
        // Arrange
        QuestionEvaluation questionToUpdate = new QuestionEvaluation(1,new RubriqueEvaluation(),new Question(), (short) 1);
        when(mockQuestionEvaluationRepository.findById(questionToUpdate.getId())).thenReturn(Optional.of(questionToUpdate));

        // Act
        QuestionEvaluation updatedQuestion = questionEvaluationService.update(questionToUpdate);

        // Assert
        verify(mockQuestionEvaluationRepository, times(1)).save(questionToUpdate); // Vérifiez que la méthode save a été appelée une fois avec la question à mettre à jour
    }



}