package com.back.csaback.Services;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Models.RubriqueQuestionId;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueQuestionRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Services.RubriqueQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RubriqueQuestionServiceTest {

    @Mock
    private RubriqueQuestionRepository rubriqueQuestionRepository;

    @Mock
    private RubriqueRepository rubriqueRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private RubriqueQuestionService rubriqueQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRubriqueQuestion() {
        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        when(rubriqueQuestionRepository.save(rubriqueQuestion)).thenReturn(rubriqueQuestion);

        RubriqueQuestion savedRubriqueQuestion = rubriqueQuestionService.saveRubriqueQuestion(rubriqueQuestion);
        assertEquals(rubriqueQuestion, savedRubriqueQuestion);
    }

    @Test
    void testDeleteAllByIdRubrique() {
        Rubrique rubrique = new Rubrique();
        doNothing().when(rubriqueQuestionRepository).deleteAllByIdRubrique(rubrique);

        assertDoesNotThrow(() -> rubriqueQuestionService.deleteAllByIdRubrique(rubrique));
        verify(rubriqueQuestionRepository, times(1)).deleteAllByIdRubrique(rubrique);
    }

    @Test
    void testDeleteById() {
        RubriqueQuestionId rubriqueQuestionId = new RubriqueQuestionId();
        doNothing().when(rubriqueQuestionRepository).deleteById(rubriqueQuestionId);

        assertDoesNotThrow(() -> rubriqueQuestionService.deleteById(rubriqueQuestionId));
        verify(rubriqueQuestionRepository, times(1)).deleteById(rubriqueQuestionId);
    }

    @Test
    void testGetRubriqueQuestion() {
        RubriqueQuestionId rubriqueQuestionId = new RubriqueQuestionId();
        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        when(rubriqueQuestionRepository.findById(rubriqueQuestionId)).thenReturn(Optional.of(rubriqueQuestion));

        Optional<RubriqueQuestion> result = rubriqueQuestionService.getRubriqueQuestion(rubriqueQuestionId);
        assertTrue(result.isPresent());
        assertEquals(rubriqueQuestion, result.get());
    }

    @Test
    void testUpdateByOrdre() {
        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        when(rubriqueQuestionRepository.save(rubriqueQuestion)).thenReturn(rubriqueQuestion);

        assertDoesNotThrow(() -> rubriqueQuestionService.updateByOrdre(rubriqueQuestion));
        verify(rubriqueQuestionRepository, times(1)).save(rubriqueQuestion);
    }

    @Test
    void testExistsByRubriqueAndQuestion() {
        Rubrique rubrique = new Rubrique();
        Question question = new Question();
        when(rubriqueQuestionRepository.existsByIdRubriqueAndIdQuestion(rubrique, question)).thenReturn(true);

        assertTrue(rubriqueQuestionService.existsByRubriqueAndQuestion(rubrique, question));
    }

    @Test
    void testGetAllRubriqueQuestionsByRubriqueId() {
        Long rubriqueId = 1L;
        Rubrique rubrique = new Rubrique();
        when(rubriqueRepository.findById(rubriqueId)).thenReturn(Optional.of(rubrique));

        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        when(rubriqueQuestionRepository.findAllByIdRubrique(rubrique)).thenReturn(Collections.singletonList(rubriqueQuestion));

        List<RubriqueQuestion> result = rubriqueQuestionService.getAllRubriqueQuestionsByRubriqueId(rubriqueId);
        assertEquals(1, result.size());
        assertEquals(rubriqueQuestion, result.get(0));
    }

    // Add more tests as needed for other methods in RubriqueQuestionService
}
