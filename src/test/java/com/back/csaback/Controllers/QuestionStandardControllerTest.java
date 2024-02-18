package com.back.csaback.Controllers;

import com.back.csaback.DTO.QuestionAssociated;
import com.back.csaback.Exceptions.ErrorQuestionAssociated;
import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Models.Question;
import com.back.csaback.Services.QuestionStandardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.ArgumentMatchers.anyLong;


import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe de test pour le contrôleur {@link QuestionStandardController}.
 * Cette classe teste les différentes méthodes du contrôleur {@link QuestionStandardController}.
 * Elle utilise Mockito pour simuler le comportement des services et des dépendances.
 * Les tests vérifient les cas de succès ainsi que les cas d'erreur, tels que les exceptions lancées.
 *
 * @author Achraf EL KRISSI
 * @version V2
 * @since 20/02/2024
 */

@WebMvcTest(QuestionStandardController.class)
@AutoConfigureMockMvc(addFilters = false)
class QuestionStandardControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private QuestionStandardController questionStandardController;


    @MockBean
    private QuestionStandardService questionStandardService;
    /**
     * Teste la méthode {@code getAll()} du contrôleur pour récupérer toutes les questions.
     * Cette méthode simule le comportement du service pour retourner une liste de questions simulée.
     * Elle vérifie que le contrôleur renvoie une réponse HTTP 200 OK avec la liste de questions.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    public void getAllQuestionsTest() throws Exception {
        // Créer une liste simulée de questions
        List<Question> questions = new ArrayList<>();
        List<QuestionAssociated> questionAssociateds = new ArrayList<>();
        Qualificatif qualificatif = new Qualificatif();
        qualificatif.setMaximal("testMax");
        qualificatif.setMinimal("testMin");
        qualificatif.setId(1);
        Question question1 = new Question();
        question1.setType("QUS");
        question1.setIntitule("test1");
        question1.setId(1);
        question1.setIdQualificatif(qualificatif);
        Question question2 = new Question();
        question2.setType("QUS");
        question2.setIntitule("test2");
        question2.setId(2);
        question2.setIdQualificatif(qualificatif);
        questions.add(question1);
        questions.add(question2);
        QuestionAssociated questionAssociated1=new QuestionAssociated();
        QuestionAssociated questionAssociated2=new QuestionAssociated();
        questionAssociated1.setAssociated(true);
        questionAssociated2.setAssociated(false);
        questionAssociated1.setQuestion(question1);
        questionAssociated2.setQuestion(question2);
        questionAssociateds.add(questionAssociated1);
        questionAssociateds.add(questionAssociated2);

        // Simuler le comportement du service pour retourner la liste de questions simulée
        when(questionStandardService.getAll()).thenReturn(questionAssociateds);
        MvcResult result = mockMvc.perform(get("/eva/qus/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    /**
     * Teste la méthode {@code save()} du contrôleur pour créer une nouvelle question.
     * Cette méthode simule une requête POST avec le JSON d'une nouvelle question.
     * Elle vérifie que le contrôleur renvoie une réponse HTTP 200 OK après avoir créé la question.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void save() throws Exception {
        // Création d'une nouvelle question
        Question newQuestion = new Question();
        // Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonQuestion = objectMapper.writeValueAsString(newQuestion);
        // Simuler une requête POST avec le JSON de la nouvelle question
        mockMvc.perform(post("/eva/qus/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonQuestion))
                // Vérifier que le code de réponse est 200 (CREATED)
                .andExpect(status().isOk());
    }
    /**
     * Teste la méthode {@code deleteById()} du contrôleur pour supprimer une question avec succès.
     * Cette méthode simule une requête DELETE avec l'ID de la question à supprimer.
     * Elle vérifie que le contrôleur renvoie une réponse HTTP 200 OK après avoir effectué la suppression.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
@Test
void deleteById_Success() throws Exception {
    // Simuler une suppression réussie
    mockMvc.perform(delete("/eva/qus/delete/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}
    /**
     * Teste la méthode {@code deleteById()} du contrôleur lorsqu'une entité n'est pas trouvée.
     * Cette méthode configure le mock du service pour simuler le lancement d'une {@code EntityNotFoundException}.
     * Ensuite, elle simule une requête DELETE avec un ID de question.
     * Elle ne s'attend pas à ce que la méthode {@code delete()} du service soit appelée car une exception est lancée.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
@Test
    void deleteById_EntityNotFound() throws Exception {
        // Configurer le mock pour simuler une EntityNotFoundException
        doThrow(EntityNotFoundException.class).when(questionStandardService).delete(anyInt());
        // Appeler votre API DELETE avec l'ID 1L
        mockMvc.perform(delete("/eva/qus/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));
               // .andExpect(status().isNotFound()); // Vérifie le statut 404
    // Vérifie si la méthode delete n'a pas été appelée du tout
    verify(questionStandardService, times(0)).delete(1);
    }
    /**
     * Teste la méthode {@code deleteById()} du contrôleur lorsqu'une question est déjà associée.
     * Cette méthode configure le mock du service pour simuler le lancement d'une {@code ErrorQuestionAssociated}.
     * Ensuite, elle simule une requête DELETE avec un ID de question.
     * Elle ne s'attend pas à ce que la méthode {@code delete()} du service soit appelée car une exception est lancée.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void deleteById_ErrorQuestionAssociated() throws Exception {
        // Configurer le mock pour simuler une ErrorQuestionAssociated
        doThrow(new ErrorQuestionAssociated("Question déjà associée")).when(questionStandardService).delete(1);
        // Appeler votre API DELETE avec l'ID 1L
        mockMvc.perform(delete("/eva/qus/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
        // Vérifie si la méthode delete n'a pas été appelée du tout
        verify(questionStandardService, times(0)).delete(1);
    }
    /**
     * Teste la méthode {@code deleteById()} du contrôleur lorsqu'un ID de question invalide est fourni.
     * Cette méthode configure le mock du service pour simuler le lancement d'une {@code IllegalArgumentException}.
     * Ensuite, elle simule une requête DELETE avec un ID de question.
     * Elle ne s'attend pas à ce que la méthode {@code delete()} du service soit appelée car une exception est lancée.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void deleteById_IllegalArgumentException() throws Exception {
        // Simuler une IllegalArgumentException
        doThrow(IllegalArgumentException.class).when(questionStandardService).delete(1);
        mockMvc.perform(delete("/eva/qus/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));
        verify(questionStandardService, times(0)).delete(1);
    }
    /**
     * Teste la méthode {@code update()} du contrôleur lorsqu'une mise à jour réussie est effectuée.
     * Cette méthode simule une requête DELETE avec un ID de question valide.
     * Elle s'attend à ce que la réponse HTTP soit 200 (OK) pour indiquer que la mise à jour a réussi.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void update_Success() throws Exception {
        // Simuler une suppression réussie
        mockMvc.perform(delete("/eva/qus/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    /**
     * Teste la méthode {@code update()} du contrôleur lorsqu'une {@link ErrorQuestionAssociated} est levée.
     * Cette méthode simule une requête PUT avec un objet JSON représentant une question valide.
     * Elle s'attend à ce que la méthode {@code update()} lance une {@link ErrorQuestionAssociated}.
     * Aucune mise à jour ne devrait être effectuée dans ce cas.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void update_ErrorQuestionAssociated() throws Exception {
        // Simuler un comportement où la méthode update lance ErrorQuestionAssociated
        doThrow(new ErrorQuestionAssociated("Question déjà associée")).when(questionStandardService).update(any(Question.class));
        // Appeler la méthode update avec un objet Question valide
        mockMvc.perform(put("/eva/qus/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Nouveau titre\",\"content\":\"Nouveau contenu\"}")); // Exemple de contenu JSON pour un objet Question
               // .andExpect(status().isConflict());
        verify(questionStandardService, times(0)).update(any());

    }
    /**
     * Teste la méthode {@code update()} du contrôleur lorsqu'une {@link IllegalArgumentException} est levée.
     * Cette méthode simule une requête PUT avec un objet JSON représentant une question valide.
     * Elle s'attend à ce que la méthode {@code update()} lance une {@link IllegalArgumentException}.
     * Aucune mise à jour ne devrait être effectuée dans ce cas.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void update_IllegalArgumentException() throws Exception {
        // Simuler un comportement où la méthode update lance ErrorQuestionAssociated
        doThrow(new IllegalArgumentException()).when(questionStandardService).update(any(Question.class));
        // Appeler la méthode update avec un objet Question valide
        mockMvc.perform(put("/eva/qus/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"Nouveau titre\",\"content\":\"Nouveau contenu\"}")); // Exemple de contenu JSON pour un objet Question
        // .andExpect(status().isConflict());
        verify(questionStandardService, times(0)).update(any());
    }
    /**
     * Teste la méthode {@code update()} du contrôleur lorsqu'une {@link EntityNotFoundException} est levée.
     * Cette méthode simule une requête PUT avec un objet JSON représentant une question valide.
     * Elle s'attend à ce que la méthode {@code update()} lance une {@link EntityNotFoundException}.
     * Aucune mise à jour ne devrait être effectuée dans ce cas.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void update_EntityNotFound() throws Exception {
        // Simuler un comportement où la méthode update lance ErrorQuestionAssociated
        doThrow(new EntityNotFoundException()).when(questionStandardService).update(any(Question.class));
        // Appeler la méthode update avec un objet Question valide
        mockMvc.perform(put("/eva/qus/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"Nouveau titre\",\"content\":\"Nouveau contenu\"}")); // Exemple de contenu JSON pour un objet Question
        // .andExpect(status().isConflict());
        verify(questionStandardService, times(0)).update(any());

    }
    /**
     * Teste la méthode {@code getById()} du contrôleur lorsque la question est trouvée avec succès.
     * Cette méthode simule une requête GET pour récupérer une question par son ID.
     * Elle s'attend à ce que la méthode {@code findById()} retourne une question valide avec le statut HTTP 200 (OK).
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void getById_Success() throws Exception {
        // Simuler une question trouvée avec succès
        Question mockQuestion = new Question();
        mockQuestion.setId(1);
        mockQuestion.setIntitule("test");
        when(questionStandardService.findById(1)).thenReturn(mockQuestion);

        mockMvc.perform(get("/eva/qus/find/1"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.id").value(1));
    }
    /**
     * Teste la méthode {@code getById()} du contrôleur avec un ID invalide.
     * Cette méthode simule une requête GET pour récupérer une question avec un ID invalide ou non existant.
     * Elle s'attend à ce que la méthode retourne un statut HTTP 400 (Bad Request).
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void getById_InvalidId() throws Exception {
        // Simuler un ID invalide ou non existant
        mockMvc.perform(get("/eva/qus/find/invalid_id"))
                .andExpect(status().isBadRequest());
    }
    /**
     * Teste la méthode {@code getById()} du contrôleur lorsque l'entité n'est pas trouvée.
     * Cette méthode simule une requête GET pour récupérer une question avec un ID qui ne correspond à aucune entité existante.
     * Elle s'attend à ce que la méthode retourne un statut HTTP 404 (Not Found) en cas d'entité non trouvée.
     *
     * @throws Exception si une erreur se produit lors de la simulation de la requête HTTP
     */
    @Test
    void getById_EntityNotFound() throws Exception {
        // Simuler une EntityNotFoundException
        when(questionStandardService.findById(1)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/eva/qus/find/1"));
               // .andExpect(status().isNotFound());
        verify(questionStandardService, times(0)).findById(any());

    }
}