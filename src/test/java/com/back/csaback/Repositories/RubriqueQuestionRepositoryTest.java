package com.back.csaback.Repositories;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Models.RubriqueQuestionId;
import com.back.csaback.Repositories.RubriqueQuestionRepository;
import com.back.csaback.Services.RubriqueQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RubriqueQuestionRepositoryTest {

    @Mock
    private RubriqueRepository rubriqueRepository;
    @Mock
    private RubriqueQuestionRepository rubriqueQuestionRepository;

    @InjectMocks
    private RubriqueQuestionService rubriqueQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExistsByIdRubriqueAndIdQuestion() {
        Rubrique rubrique = new Rubrique();
        Question question = new Question();
        when(rubriqueQuestionRepository.existsByIdRubriqueAndIdQuestion(rubrique, question)).thenReturn(true);

        assertTrue(rubriqueQuestionService.existsByRubriqueAndQuestion(rubrique, question));
    }

    @Test
    void testDeleteAllByIdRubrique() {
        Rubrique rubrique = new Rubrique();
        doNothing().when(rubriqueQuestionRepository).deleteAllByIdRubrique(rubrique);

        assertDoesNotThrow(() -> rubriqueQuestionService.deleteAllByIdRubrique(rubrique));
        verify(rubriqueQuestionRepository, times(1)).deleteAllByIdRubrique(rubrique);
    }

    @Test
    void testGetAllRubriqueQuestionsByRubriqueId() {
        // Create a Rubrique object
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1L);

        // Create a list of RubriqueQuestion objects
        List<RubriqueQuestion> rubriqueQuestions = List.of(
                new RubriqueQuestion(new RubriqueQuestionId(1L, 1L), rubrique, new Question(), 1L),
                new RubriqueQuestion(new RubriqueQuestionId(2L, 2L), rubrique, new Question(), 2L),
                new RubriqueQuestion(new RubriqueQuestionId(3L, 3L), rubrique, new Question(), 3L)
        );

        // Mock the behavior of the rubriqueRepository
        when(rubriqueRepository.findById(rubrique.getId())).thenReturn(java.util.Optional.of(rubrique));

        // Mock the behavior of the rubriqueQuestionRepository
        when(rubriqueQuestionRepository.findAllByIdRubrique(rubrique)).thenReturn(rubriqueQuestions);

        // Call the method under test
        List<RubriqueQuestion> result = rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubrique.getId());

        // Assert the result
        assertEquals(rubriqueQuestions.size(), result.size());
        assertEquals(rubriqueQuestions, result);
    }

    // Add more tests as needed for other methods in RubriqueQuestionRepository
}
