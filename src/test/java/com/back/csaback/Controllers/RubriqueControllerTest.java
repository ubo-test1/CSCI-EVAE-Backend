package com.back.csaback.Controllers;

import com.back.csaback.DTO.RubQRequest;
import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Services.RubriqueService;
import com.back.csaback.Services.Tooltip;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)//Elle permet d'utiliser les fonctionnalités de Spring, telles que l'injection de dépendances et la configuration de contexte d'application, dans les tests.
@WebMvcTest(RubriqueController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RubriqueControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RubriqueService rubriqueService;
    @MockBean
    private RubriqueController rubriqueController;
    @MockBean
    private RubriqueRepository rubriqueRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private Tooltip tooltip;

    @Test
    void testCreateRubrique() throws Exception {
        // Créer une instance de Rubrique pour la requête POST
        Rubrique rubrique = new Rubrique();
        rubrique.setDesignation("example");
        // Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRubrique = objectMapper.writeValueAsString(rubrique);
        when(tooltip.getRoleFromToken(anyString())).thenReturn("ADM");

        // Simuler la requête POST avec un en-tête Authorization vide
        mockMvc.perform(post("/rub/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "")
                        .content(jsonRubrique))
                .andExpect(status().isOk());
    }
    @Test
    void testUpdateStd() throws Exception {
        // Création d'un objet Rubrique simulé
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);
        rubrique.setType("RBS");
        rubrique.setDesignation("Nouvelle désignation");
        rubrique.setOrdre(2L);

        // Mock du comportement de RubriqueService.findById()
        when(rubriqueService.findById(eq(rubrique.getId()))).thenReturn(new Rubrique());

        // Conversion de l'objet Rubrique en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String rubriqueJson = objectMapper.writeValueAsString(rubrique);

        // Utilisation de mockMvc pour simuler une requête POST
        mockMvc.perform(post("/rub/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rubriqueJson))
                .andExpect(status().isOk());
    }
    @Test
    void testConsulterStd() throws Exception {
        int rubriqueId = 1;

        // Mock du comportement de RubriqueService.findById()
        Rubrique rubrique = new Rubrique();
        when(rubriqueService.findById(eq(rubriqueId))).thenReturn(rubrique);

        // Utilisation de mockMvc pour simuler une requête GET
        mockMvc.perform(get("/rub/consulter/{id}", rubriqueId))
                .andExpect(status().isOk());
    }
    @Test
    void testGetAll() throws Exception {
        // Mock du comportement de RubriqueService.getAllStd()
        List<RubriqueAssociated> mockRubriques = new ArrayList<>();
        // Ajoutez des éléments à la liste si nécessaire

        when(rubriqueService.getAllStd()).thenReturn(mockRubriques);

        // Utilisation de mockMvc pour simuler une requête GET
        mockMvc.perform(get("/rub/allStd"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        // ID de la rubrique à supprimer
        int idToDelete = 1;

        // Appel du endpoint de suppression avec l'ID de la rubrique
        mockMvc.perform(delete("/rub/deleteStd/{id}", idToDelete))
                .andExpect(status().isOk());

    }
    @Test
    public void testAddQ() throws Exception {
        // Création de la requête JSON
        JSONObject requestBody = new JSONObject();
        requestBody.put("rubriqueId", 1);
        requestBody.put("qList", Arrays.asList(1, 2, 3));

        // Simuler le comportement de RubriqueService
        doNothing().when(rubriqueService).assignQList(any(RubQRequest.class));

        // Effectuer la requête POST
        mockMvc.perform(post("/rub/assignQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody.toString()))
                .andExpect(status().isOk());


    }



}





