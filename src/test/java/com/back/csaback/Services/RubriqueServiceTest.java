package com.back.csaback.Services;

import com.back.csaback.DTO.RubQRequest;
import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.DTO.RubriqueDetails;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubQuesRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Services.RubriqueService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RubriqueServiceTest {
    @Mock
    private RubQuesRepository rubQuesRepository;
    @Mock
    private EvaRubRepository err;
    @Mock
    private RubriqueRepository rubriqueRepository;
    @Spy
    @InjectMocks
    private RubriqueService rubriqueService;
    @Mock
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        // Initialiser les comportements simulés pour les mocks
        MockitoAnnotations.initMocks(this);

        //when(err.findAllByIdRubrique(any())).thenReturn(new ArrayList<>());
    }

    @Test
    public void testCreateRubStd() {
        // Given
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("Test Rubrique");

        // Mock du comportement du repository
        when(rubriqueRepository.existsByDesignation(rubrique.getDesignation())).thenReturn(false);
        when(rubriqueRepository.save(rubrique)).thenReturn(rubrique);

        // When
        Rubrique createdRubrique = rubriqueService.createRubStd(rubrique);

        // Then
        assertNotNull(createdRubrique);
        assertEquals("RBS", createdRubrique.getType());
        verify(rubriqueRepository, times(1)).existsByDesignation(rubrique.getDesignation());
        verify(rubriqueRepository, times(1)).save(rubrique);
    }

    @Test
    public void testCreateRubStdWithExistingDesignation() {
        // Given
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("Test Rubrique");

        // Mock du comportement du repository
        when(rubriqueRepository.existsByDesignation(rubrique.getDesignation())).thenReturn(true);

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> rubriqueService.createRubStd(rubrique));
        verify(rubriqueRepository, times(1)).existsByDesignation(rubrique.getDesignation());
        verify(rubriqueRepository, never()).save(rubrique);
    }

    @Test
    void testGetRubQuest() {
        // Créer des données de test
        Rubrique rubrique = new Rubrique();
        List<RubriqueQuestion> rubriqueQuestions = new ArrayList<>();
        // Ajouter des questions à la liste
        rubriqueQuestions.add(new RubriqueQuestion());
        rubriqueQuestions.add(new RubriqueQuestion());

        // Simuler le comportement du repository
        when(rubQuesRepository.findAllByIdRubrique(rubrique)).thenReturn(rubriqueQuestions);

        // Appeler la méthode à tester
        List<Question> questions = rubriqueService.getRubQuest(rubrique);

        // Vérifier le résultat
        assertEquals(2, questions.size());
    }

    @Test
    void testGetAllStd() {
        // Créer des données de test
        List<Rubrique> rubriques = new ArrayList<>();
        rubriques.add(new Rubrique());
        rubriques.add(new Rubrique());

        // Simuler le comportement du repository
        when(rubriqueRepository.findAll()).thenReturn(rubriques);

        // Appeler la méthode à tester
        List<RubriqueAssociated> rubriqueAssociateds = rubriqueService.getAllStd();

        // Vérifier le résultat
        assertEquals(2, rubriqueAssociateds.size());
        // Vous devez également vérifier les valeurs des objets RubriqueAssociated selon vos besoins
    }

    @Test
    void testCreateRubPers() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("Test Rubrique");
        rubrique.setType("RBP");

        // Simuler le comportement du repository
        when(rubriqueRepository.existsByDesignation(rubrique.getDesignation())).thenReturn(false);
        when(rubriqueRepository.save(rubrique)).thenReturn(rubrique);

        // Appeler la méthode à tester
        Rubrique createdRubrique = rubriqueService.createRubPers(rubrique);

        // Vérifier le résultat
        assertNotNull(createdRubrique);
        assertEquals("RBP", createdRubrique.getType());
        // Vous pouvez également vérifier d'autres attributs selon vos besoins
    }
    @Test
    void testCreateRubPers_RubriqueExists() {
        // Setup
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("Existing Rubrique");
        when(rubriqueRepository.existsByDesignation("Existing Rubrique")).thenReturn(true);

            rubriqueService.createRubPers(rubrique);

        verify(rubriqueRepository, never()).save(any(Rubrique.class));
    }

    @Test
    void testDelete() {
        // Créer un ID de rubrique pour le test
        Integer rubriqueId = 1;

        // Simuler le comportement du repository
        Rubrique rubrique = new Rubrique();
        rubrique.setId(rubriqueId);
        when(rubriqueRepository.findById(rubriqueId)).thenReturn(Optional.of(rubrique));
        when(rubQuesRepository.findAllByIdRubrique(rubrique)).thenReturn(Collections.emptyList());

        // Appeler la méthode à tester
        rubriqueService.delete(rubriqueId);

        // Vérifier que les méthodes du repository ont été appelées correctement
        verify(rubriqueRepository, times(1)).findById(rubriqueId);
        verify(rubQuesRepository, times(1)).findAllByIdRubrique(rubrique);
        verify(rubQuesRepository, times(1)).deleteAll(Collections.emptyList());
        verify(rubriqueRepository, times(1)).delete(rubrique);
    }


    @Test
    void testCreateRubStd_WithNonExistingDesignation() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("Test Rubrique");

        // Simuler le comportement du repository
        when(rubriqueRepository.existsByDesignation(rubrique.getDesignation())).thenReturn(false);
        when(rubriqueRepository.save(rubrique)).thenReturn(rubrique);

        // Appeler la méthode à tester
        Rubrique createdRubrique = rubriqueService.createRubStd(rubrique);

        // Vérifier le résultat
        assertNotNull(createdRubrique);
        assertEquals("RBS", createdRubrique.getType());
        verify(rubriqueRepository).existsByDesignation(rubrique.getDesignation());
        verify(rubriqueRepository).save(rubrique);
    }
    @Test
    void testCreateRubStd_WithExistingDesignation() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("Existing Rubrique");

        // Simuler le comportement du repository
        when(rubriqueRepository.existsByDesignation(rubrique.getDesignation())).thenReturn(true);

        // Vérifier que l'appel à createRubStd lance une exception avec une désignation existante
        assertThrows(IllegalArgumentException.class, () -> rubriqueService.createRubStd(rubrique));

        // Vérifier que le repository n'est pas appelé pour sauvegarder la rubrique
        verify(rubriqueRepository, never()).save(rubrique);
    }


    @Test
    public void testCheckIfUsed_RubriqueNotUsed() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Simuler le comportement du repository
        when(err.findAllByIdRubrique(rubrique)).thenReturn(Collections.emptyList());

        // Appeler la méthode à tester
        boolean result = rubriqueService.checkIfUsed(rubrique);

        // Vérifier le résultat
        assertFalse(result);
        // Assurez-vous que la méthode err.findAllByIdRubrique(rubrique) a été appelée une fois
        verify(err, times(1)).findAllByIdRubrique(rubrique);
    }

    @Test
    public void testCheckIfUsed_RubriqueUsed() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Simuler le comportement du repository
        List<RubriqueEvaluation> rubriqueEvaluations = Collections.singletonList(new RubriqueEvaluation());
        when(err.findAllByIdRubrique(rubrique)).thenReturn(rubriqueEvaluations);

        // Appeler la méthode à tester
        boolean result = rubriqueService.checkIfUsed(rubrique);

        // Vérifier le résultat
        assertTrue(result);
        // Assurez-vous que la méthode err.findAllByIdRubrique(rubrique) a été appelée une fois
        verify(err, times(1)).findAllByIdRubrique(rubrique);
    }
    @Test
    public void testUpdateRub_RubriqueNotUsed() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Simuler le comportement du service pour vérifier si la rubrique est utilisée
        when(rubriqueService.checkIfUsed(rubrique)).thenReturn(false);

        // Simuler le comportement du repository pour enregistrer la rubrique
        when(rubriqueRepository.save(rubrique)).thenReturn(rubrique);

        // Appeler la méthode à tester
        Rubrique updatedRubrique = rubriqueService.updateRub(rubrique);

        // Vérifier le résultat
        assertNotNull(updatedRubrique);
        assertEquals(rubrique, updatedRubrique);
        // Assurez-vous que la méthode rr.save(rubrique) a été appelée une fois
        verify(rubriqueRepository, times(1)).save(rubrique);
    }
    @Test
    public void testUpdateRub_RubriqueUsed() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Simuler le comportement du service pour vérifier si la rubrique est utilisée
        when(rubriqueService.checkIfUsed(rubrique)).thenReturn(true);

        // Appeler la méthode à tester et vérifier qu'elle lance une exception
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            rubriqueService.updateRub(rubrique);
        });

        // Vérifier le message d'exception
        assertEquals("La rubrique est utilise dans une evaluation", exception.getMessage());
        // Assurez-vous que la méthode rr.save(rubrique) n'est pas appelée
        verify(rubriqueRepository, never()).save(rubrique);
    }

    @Test
    public void testConsulterRubriqueComp_Success() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Créer une liste de questions de test
        List<RubriqueQuestion> rubriqueQuestions = new ArrayList<>();
        rubriqueQuestions.add(new RubriqueQuestion());
        rubriqueQuestions.add(new RubriqueQuestion());

        // Simuler le comportement de la méthode getRubQuest
        when(rubQuesRepository.findAllByIdRubrique(rubrique)).thenReturn(rubriqueQuestions);

        // Appeler la méthode à tester
        RubriqueDetails rubriqueDetails = rubriqueService.consulterRubriqueComp(rubrique);

        // Vérifier le résultat
        assertNotNull(rubriqueDetails);
        assertEquals(rubrique, rubriqueDetails.getRubrique());
        assertEquals(2, rubriqueDetails.getQuestions().size());
    }

    @Test
    public void testConsulter_RubriqueExists() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Simuler le comportement de la méthode findById pour retourner la rubrique de test
        when(rubriqueRepository.findById(1)).thenReturn(Optional.of(rubrique));

        // Appeler la méthode à tester
        Rubrique result = rubriqueService.consulter(1);

        // Vérifier que la rubrique retournée est la même que celle de test
        assertNotNull(result);
        assertEquals(rubrique, result);
    }

    @Test
    public void testConsulter_RubriqueNotFound() {
        // Simuler le comportement de la méthode findById pour retourner une option vide
        when(rubriqueRepository.findById(1)).thenReturn(Optional.empty());

        // Appeler la méthode à tester
        Rubrique result = rubriqueService.consulter(1);

        // Vérifier que la rubrique retournée est null
        assertNull(result);
    }

    @Test
    public void testConsulter_Exception() {
        // Simuler le comportement de la méthode findById pour lancer une exception
        when(rubriqueRepository.findById(1)).thenThrow(new RuntimeException("Erreur lors de la récupération de la rubrique"));

        // Appeler la méthode à tester
        Rubrique result = rubriqueService.consulter(1);

        // Vérifier que la rubrique retournée est null
        assertNull(result);
    }

    @Test
    public void testIsCompose_True() {
        // Créer une rubrique de test avec des relations de questions
        Rubrique rubrique = new Rubrique();
        List<RubriqueQuestion> rubriqueQuestions = new ArrayList<>();
        rubriqueQuestions.add(new RubriqueQuestion());
        rubriqueQuestions.add(new RubriqueQuestion());

        // Simuler le comportement de la méthode findAllByIdRubrique pour retourner les relations de questions
        when(rubQuesRepository.findAllByIdRubrique(rubrique)).thenReturn(rubriqueQuestions);

        // Appeler la méthode à tester
        boolean result = rubriqueService.isCompose(rubrique);

        // Vérifier que la méthode retourne true car la rubrique a des relations avec des questions
        assertTrue(result);
    }

    @Test
    public void testIsCompose_False() {
        // Créer une rubrique de test sans relation de questions
        Rubrique rubrique = new Rubrique();

        // Simuler le comportement de la méthode findAllByIdRubrique pour retourner une liste vide de relations de questions
        when(rubQuesRepository.findAllByIdRubrique(rubrique)).thenReturn(new ArrayList<>());

        // Appeler la méthode à tester
        boolean result = rubriqueService.isCompose(rubrique);

        // Vérifier que la méthode retourne false car la rubrique n'a pas de relation avec des questions
        assertFalse(result);
    }

    @Test
    public void testFindById_RubriqueExists() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Simuler le comportement du repository pour retourner la rubrique lorsque findById est appelée avec l'ID de la rubrique
        when(rubriqueRepository.findById(1)).thenReturn(Optional.of(rubrique));

        // Appeler la méthode à tester
        Rubrique result = rubriqueService.findById(1);

        // Vérifier que la méthode retourne la rubrique attendue
        assertEquals(rubrique, result);
    }

    @Test
    public void testFindById_RubriqueDoesNotExist() {
        // Simuler le comportement du repository pour retourner une rubrique vide lorsque findById est appelée avec un ID inexistant
        when(rubriqueRepository.findById(1)).thenReturn(Optional.empty());

        // Vérifier que la méthode lance une EntityNotFoundException lorsqu'aucune rubrique n'est trouvée avec l'ID spécifié
        assertThrows(EntityNotFoundException.class, () -> rubriqueService.findById(1));
    }

    @Test
    public void testAssignQ() {
        // Créer une rubrique de test
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        // Créer une question de test
        Question question = new Question();
        question.setId(1);

        // Simuler le comportement du repository pour retourner la rubrique lorsque findById est appelée avec l'ID de la rubrique
        when(rubriqueRepository.findById(1)).thenReturn(java.util.Optional.of(rubrique));

        // Simuler le comportement du repository pour retourner la dernière ordre utilisée (par exemple, null si aucune rubrique n'a encore été assignée)
        when(rubriqueService.getLastOrdre(1)).thenReturn(null);

        // Appeler la méthode à tester
        rubriqueService.assignQ(1, question);

        // Vérifier que la méthode save du repository de RubriqueQuestion a été appelée avec le bon objet RubriqueQuestion
        verify(rubQuesRepository, times(1)).save(any(RubriqueQuestion.class));
    }

    @Test
    public void testGetLastOrdre() {
        // Définir l'ID de rubrique pour lequel nous voulons récupérer le dernier ordre
        Integer rubriqueId = 1;

        // Définir l'ordre maximal attendu
        Long expectedMaxOrdre = 10L;

        // Simuler le comportement du repository pour retourner l'ordre maximal attendu pour l'ID de rubrique donné
        when(rubQuesRepository.findMaxOrdreByRubriqueId(rubriqueId)).thenReturn(expectedMaxOrdre);

        // Appeler la méthode à tester
        Long actualMaxOrdre = rubriqueService.getLastOrdre(rubriqueId);

        // Vérifier que l'ordre maximal retourné correspond à l'ordre attendu
        assertEquals(expectedMaxOrdre, actualMaxOrdre);
    }

    @Test
    public void testAssignQList() {
        // Création de la demande de rubrique et de liste de questions
        RubQRequest rubQRequest = new RubQRequest();
        rubQRequest.setRubriqueId(1);
        List<Integer> questionIds = Arrays.asList(1, 2, 3);
        rubQRequest.setQList(questionIds);
        // Mock des comportements des repositories pour simuler les données existantes
        when(rubriqueRepository.findById(1)).thenReturn(Optional.of(new Rubrique())); // Simule une rubrique existante
        for (Integer questionId : questionIds) {
            when(questionRepository.findById(questionId)).thenReturn(Optional.of(new Question())); // Simule chaque question existante
            Optional<Question> question = questionRepository.findById(questionId);
            when(rubriqueService.checkQAssigned(1, question.get())).thenReturn(false); // Simule que chaque question n'est pas déjà attribuée
        }

        // Appel de la méthode à tester
        rubriqueService.assignQList(rubQRequest);

        // Vérification que la méthode assignQ a été appelée pour chaque question dans la liste
        for (Integer questionId : questionIds) {
            Optional<Question> question = questionRepository.findById(questionId);
            verify(rubriqueService).assignQ(1, question.get());
        }
    }
    @Test
    public void testCheckQAssigned() {
        // Création d'une rubrique avec des questions assignées
        Question question =new Question();
        question.setId(1);
        List<Question> questions=new ArrayList<>();
        questions.add(question);
        // Mock du comportement du repository pour retourner la rubrique avec les questions assignées
        when(rubQuesRepository.findQuestionsByRubriqueId(1)).thenReturn(questions);
        // Vérification que la question assignée est reconnue comme telle
        assertTrue(rubriqueService.checkQAssigned(1, question));
        // Création d'une question non assignée à la rubrique
        Question unassignedQuestion = new Question();
        unassignedQuestion.setId(2);
        // Vérification que la question non assignée n'est pas reconnue comme telle
        assertFalse(rubriqueService.checkQAssigned(1, unassignedQuestion));
    }
}
