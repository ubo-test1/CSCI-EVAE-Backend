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

}
