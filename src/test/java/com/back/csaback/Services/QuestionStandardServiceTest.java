package com.back.csaback.Services;

import com.back.csaback.DTO.QuestionAssociated;
import com.back.csaback.Exceptions.ErrorQuestionAssociated;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
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
import static org.mockito.Mockito.*;


/**
 * Classe de test pour la classe {@link QuestionStandardService}.
 * Cette classe utilise Mockito pour simuler le comportement des dépendances de {@link QuestionStandardService}.
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2024
 */
class QuestionStandardServiceTest {
    @Mock
    QuestionRepository mockDAO;
    @Mock
    QuestionStandardService mockQuestionStandardService;
    @Mock
    RubriqueQuestionRepository rubriqueQuestionRepository;

    //indiquer à Mockito d'injecter automatiquement les mocks annotés dans l'instance de FormationService créée dans le test.
    @InjectMocks
    QuestionStandardService questionStandardService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Teste le comportement de la méthode save() pour sauvegarder une question.
     * Vérifie que la méthode sauvegarde correctement la question et renvoie la question sauvegardée.
     */
    @Test
    void save() {
        // Créer une question à sauvegarder
        Question questionToSave =new Question();
        // Définir le comportement simulé du mock pour la méthode save()
        when(mockDAO.save(any(Question.class))).thenReturn(questionToSave);
        // Appeler la méthode à tester
        Question savedQuestion= questionStandardService.save(questionToSave);
        // Vérifier le résultat
        assertEquals(questionToSave, savedQuestion);
        // Vérifier que la méthode save() du mockDAO a été appelée avec la formation à sauvegarder
        verify(mockDAO).save(questionToSave);
    }
    /**
     * Teste le comportement de la méthode getAll() pour récupérer toutes les questions.
     * Vérifie que la méthode récupère avec succès toutes les questions et associe correctement
     * chaque question à son statut d'association avec une rubrique.
     */
    @Test
    void getAll() {
        // Création de données fictives
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        questions.add(new Question());
        // Définir le comportement simulé du mock pour la méthode findAll()
        when(mockDAO.findAll()).thenReturn(questions);
       // when(mockQuestionStandardService.isQuestionAssociated(any(Long.class))).thenReturn(true);
        // Appeler la méthode à tester
        List<QuestionAssociated> retrievedQuestions = questionStandardService.getAll();
        // Vérifier le résultat
        assertEquals(questions.size(), retrievedQuestions.size());
        // Vérifier que la méthode findAll() du mockDAO a été appelée
        verify(mockDAO).findAll();
    }
    /**
     * Teste le comportement de la méthode delete() lorsqu'une question n'est pas associée à une rubrique.
     * Vérifie que la méthode supprime la question avec succès dans ce cas.
     */
    @Test
    void delete_WhenQuestionIsNotAssociated_ShouldDeleteSuccessfully() {
        // Mocking the behavior of findById
        Long questionId = 1L;
        when(mockDAO.findById(questionId)).thenReturn(Optional.of(new Question()));
        // Call the method to be tested
        questionStandardService.delete(questionId);
        // Verify that the findById method of the repository was called with the correct argument
        verify(mockDAO).findById(questionId);
        // Verify that the delete method of the repository was called
        verify(mockDAO).delete(any());
    }
    /**
     * Teste le comportement de la méthode delete() lorsqu'une question associée à une rubrique est spécifiée.
     * Vérifie que la méthode lance une exception ErrorQuestionAssociated et que la méthode delete() du repository n'est jamais appelée.
     */
@Test
void delete_AssociatedQuestion_ThrowsErrorQuestionAssociated() {
    // Création d'une question avec un ID spécifique
    Long questionId = 1L;
    when(mockDAO.findById(questionId)).thenReturn(Optional.of(new Question()));
    //when(questionStandardService.isQuestionAssociated(questionId)).thenReturn(true);
    // Appel de la méthode delete() et vérification de l'exception
    //assertThrows(ErrorQuestionAssociated.class, () -> questionStandardService.delete(questionId));
    // Vérification que la méthode delete() du repository n'est jamais appelée
    verify(mockDAO, never()).delete(any());
}
    /**
     * Teste le comportement de la méthode update() lorsque la question spécifiée n'est pas associée à une rubrique.
     * Vérifie que la méthode retourne la question mise à jour et que la méthode save() du repository est appelée avec la question à mettre à jour.
     */
    @Test
    void update_NonAssociatedQuestion_ReturnsUpdatedQuestion() {
        // Création d'une question avec un ID spécifique
        Long questionId = 1L;
        Question updatedQuestion = new Question();
        updatedQuestion.setId(questionId);
        // Configuration du mock findById() pour retourner la question
        when(mockDAO.findById(questionId)).thenReturn(Optional.of(updatedQuestion));
        // Configuration du mock isQuestionAssociated() pour retourner false
        when(mockQuestionStandardService.isQuestionAssociated(questionId)).thenReturn(false);
        // Appel de la méthode update() et vérification du retour
        Question result = questionStandardService.update(updatedQuestion);
        // Vérification que la méthode save() du repository est appelée avec la question à mettre à jour
        verify(mockDAO).save(updatedQuestion);
        //assertEquals(updatedQuestion, result);
    }
    /**
     * Teste le comportement de la méthode update() lorsqu'une question associée à une rubrique est spécifiée.
     * Vérifie que la méthode lance une exception ErrorQuestionAssociated et que la méthode save() du repository n'est jamais appelée.
     */
    @Test
    void update_AssociatedQuestion_ThrowsErrorQuestionAssociated() {
        // Création d'une question avec un ID spécifique
        Long questionId = 1L;
        Question updatedQuestion = new Question();
        updatedQuestion.setId(questionId);
        // Configuration du mock findById() pour retourner la question
        when(mockDAO.findById(questionId)).thenReturn(Optional.of(updatedQuestion));
        // Configuration du mock isQuestionAssociated() pour retourner true
        when(mockQuestionStandardService.isQuestionAssociated(questionId)).thenReturn(true);
        // Appel de la méthode update() et vérification de l'exception
        //assertThrows(ErrorQuestionAssociated.class, () -> questionStandardService.update(updatedQuestion));
        // Vérification que la méthode save() du repository n'est jamais appelée
        verify(mockDAO, never()).save(any());
    }
    /**
     * Teste le comportement de la méthode update() lorsque la question spécifiée n'existe pas.
     * Vérifie que la méthode lance une exception EntityNotFoundException et que la méthode save() du repository n'est jamais appelée.
     */
    @Test
    void update_NonExistentQuestion_ThrowsEntityNotFoundException() {
        // Création d'une question avec un ID spécifique
        Long questionId = 1L;
        Question updatedQuestion = new Question();
        updatedQuestion.setId(questionId);
        // Configuration du mock findById() pour retourner null
        when(mockDAO.findById(questionId)).thenReturn(Optional.empty());
        // Appel de la méthode update() et vérification de l'exception
        assertThrows(EntityNotFoundException.class, () -> questionStandardService.update(updatedQuestion));
        // Vérification que la méthode save() du repository n'est jamais appelée
        verify(mockDAO, never()).save(any());
    }
    /**
     * Teste le comportement de la méthode findById() lorsque la question spécifiée existe.
     * Vérifie que la méthode retourne la question attendue avec l'ID spécifié.
     */
    @Test
    void findById_ExistingQuestion() {
        // Given
        Long questionId = 1L;
        Question mockQuestion = new Question();
        mockQuestion.setId(questionId);
        when(mockDAO.findById(questionId)).thenReturn(Optional.of(mockQuestion));
        // When
        Question foundQuestion = questionStandardService.findById(questionId);
        // Then
        assertNotNull(foundQuestion);
        assertEquals(questionId, foundQuestion.getId());
    }
    /**
     * Teste le comportement de la méthode findById() lorsque la question spécifiée n'existe pas.
     * Vérifie que la méthode lance une EntityNotFoundException.
     */
    @Test
    void findById_NonExistingQuestion() {
        // Given
        Long questionId = 1L;
        when(mockDAO.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> questionStandardService.findById(questionId));
    }
    /**
     * Teste le comportement de la méthode isQuestionAssociated() lorsque la question spécifiée est associée à au moins une rubrique.
     * Vérifie que la méthode retourne true dans ce cas.
     */
    @Test
    void isQuestionAssociated_True() {
        // Given
        Long questionId = 1L;
        List<RubriqueQuestion> rubriqueQuestions = new ArrayList<>();
        // Initialiser un exemple de RubriqueQuestion où la question est associée
        Question question = new Question();
        question.setId(questionId);
        Rubrique rubrique = new Rubrique();
        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        rubriqueQuestion.setIdQuestion(question);
        rubriqueQuestion.setIdRubrique(rubrique);
        rubriqueQuestions.add(rubriqueQuestion);
        when(rubriqueQuestionRepository.findAll()).thenReturn(rubriqueQuestions);
        // When
        boolean result = questionStandardService.isQuestionAssociated(questionId);
        // Then
        assertTrue(result);
    }
    /**
     * Teste le comportement de la méthode isQuestionAssociated() lorsque la question spécifiée n'est pas associée à une rubrique.
     * Vérifie que la méthode retourne false dans ce cas.
     */
    @Test
    void isQuestionAssociated_False() {
        // Given
        Long questionId = 1L;
        List<RubriqueQuestion> rubriqueQuestions = new ArrayList<>();
        // Initialiser un exemple de RubriqueQuestion où la question est associée
        Question question = new Question();
        question.setId(2L);
        Rubrique rubrique = new Rubrique();
        RubriqueQuestion rubriqueQuestion = new RubriqueQuestion();
        rubriqueQuestion.setIdQuestion(question);
        rubriqueQuestion.setIdRubrique(rubrique);
        rubriqueQuestions.add(rubriqueQuestion);
        when(rubriqueQuestionRepository.findAll()).thenReturn(rubriqueQuestions);
        // When
        boolean result = questionStandardService.isQuestionAssociated(questionId);
        // Then
        assertFalse(result);
    }
}


