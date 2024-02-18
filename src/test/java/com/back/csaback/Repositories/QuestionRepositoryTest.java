package com.back.csaback.Repositories;

import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le repository {@link QuestionRepository}.
 * Cette classe teste les opérations CRUD (Create, Read, Update, Delete) sur les questions.
 * Elle utilise l'annotation {@link DataJpaTest} pour configurer un contexte de test de base de données.
 * Les dépendances sont simulées à l'aide de Mockito.
 *
 * @author Achraf EL KRISSI
 * @version V1
 * @since 14/02/2024
 */

@DataJpaTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QualificatifRepository qualificatifRepository;
    @BeforeEach
    void setUp() {
    }
    /**
     * Teste la sauvegarde d'une question dans le repository.
     * Vérifie que la question sauvegardée a les mêmes attributs que la question d'origine.
     */
    @Test
    public void testSaveQuestion() {
        Qualificatif qualificatif=new Qualificatif();

        qualificatif.setId(1);
        qualificatif.setMinimal("testMin");
        qualificatif.setMaximal("testMax");
        qualificatifRepository.save(qualificatif);

        Question questionToSave = new Question();
        questionToSave.setId(1);
        questionToSave.setType("QUS");
        questionToSave.setIntitule("test");
        questionToSave.setIdQualificatif(qualificatif);
        Question savedQuestion = questionRepository.save(questionToSave);
        assertEquals(questionToSave.getType(), savedQuestion.getType());
        assertEquals(questionToSave.getIntitule(), savedQuestion.getIntitule());
    }
    /**
     * Teste la récupération de toutes les questions depuis le repository.
     * Vérifie que le nombre de questions récupérées correspond au nombre de questions enregistrées.
     */
    @Test
    public void testFindAllQuestion() {
        Qualificatif qualificatif=new Qualificatif();
        qualificatif.setId(1);
        qualificatif.setMinimal("testMin");
        qualificatif.setMaximal("testMax");
        qualificatifRepository.save(qualificatif);
        Question question1= new Question();
        question1.setId(1);
        question1.setType("QUS");
        question1.setIntitule("test1");
        question1.setIdQualificatif(qualificatif);
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setId(2);
        question2.setType("QUS");
        question2.setIntitule("test2");
        question2.setIdQualificatif(qualificatif);
        questionRepository.save(question2);

        List<Question> allQuestions = questionRepository.findAll();

        assertEquals(2, allQuestions.size());
    }
    /**
     * Teste la récupération d'une question par son ID depuis le repository.
     * Vérifie que la question récupérée a les mêmes attributs que la question sauvegardée.
     */
    @Test
    public void testFindByIdQuestion() {
        Question questionToSave = new Question();
        questionToSave.setType("Type");
        questionToSave.setIntitule("Intitule");
        Qualificatif qualificatif=new Qualificatif();
        qualificatif.setId(1);
        qualificatif.setMinimal("testMin");
        qualificatif.setMaximal("testMax");
        qualificatifRepository.save(qualificatif);
        questionToSave.setIdQualificatif(qualificatif);
        Question savedQuestion = questionRepository.save(questionToSave);
        Optional<Question> retrievedQuestionOptional = questionRepository.findById(savedQuestion.getId());
        assertTrue(retrievedQuestionOptional.isPresent());
        assertEquals(savedQuestion.getType(), retrievedQuestionOptional.get().getType());
        assertEquals(savedQuestion.getIntitule(), retrievedQuestionOptional.get().getIntitule());
    }
    /**
     * Teste la suppression d'une question depuis le repository.
     * Vérifie que la question est correctement supprimée et que la liste des questions est vide après la suppression.
     */
    @Test
    public void testDeleteQuestion() {
        Qualificatif qualificatif = new Qualificatif();
        qualificatif.setId(1);
        qualificatif.setMinimal("testMin");
        qualificatif.setMaximal("testMax");
        qualificatifRepository.save(qualificatif);
        // Création et sauvegarde d'une question
        Question question= new Question();
        question.setId(1);
        question.setType("QUS");
        question.setIntitule("test");
        question.setIdQualificatif(qualificatif);
        questionRepository.save(question);
        // Suppression de la question
        questionRepository.delete(question);

        // Vérification que la question a été supprimée
        List<Question> allQuestions = questionRepository.findAll();
        assertTrue(allQuestions.isEmpty(), "La liste des questions devrait être vide après la suppression");
    }
    /**
     * Teste la mise à jour d'une question dans le repository.
     * Vérifie que la question est correctement mise à jour avec les nouvelles données.
     */
    @Test
    public void testUpdateQuestion() {
        Qualificatif qualificatif = new Qualificatif();
        qualificatif.setId(1);
        qualificatif.setMinimal("testMin");
        qualificatif.setMaximal("testMax");
        qualificatifRepository.save(qualificatif);

        // Création et sauvegarde d'une question
        Question question = new Question();
        question.setId(1);
        question.setType("QUS");
        question.setIntitule("test");
        question.setIdQualificatif(qualificatif);
        questionRepository.save(question);

        // Modification de la question
        question.setIntitule("nouveauTest");
        questionRepository.save(question);

        // Récupération de la question modifiée
        Question updatedQuestion = questionRepository.findById(question.getId()).orElse(null);

        // Vérification que la question a été correctement mise à jour
        assertNotNull(updatedQuestion);
        assertEquals("nouveauTest", updatedQuestion.getIntitule());
    }
}

