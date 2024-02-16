package com.back.csaback.Controllers;

import com.back.csaback.Models.Rubrique;
import com.back.csaback.Services.RubriqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RubriqueControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RubriqueService rubriqueService;

    @InjectMocks
    private RubriqueController rubriqueController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rubriqueController).build();
    }

    @Test
    public void testSaveRubrique() throws Exception {
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1L);
        rubrique.setType("Type");
        rubrique.setDesignation("Designation");

        when(rubriqueService.saveRubrique(any(Rubrique.class))).thenReturn(rubrique);

        mockMvc.perform(post("/rubrique/create")
                        .contentType("application/json")
                        .content("{\"id\":1,\"type\":\"Type\",\"designation\":\"Designation\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("Type"))
                .andExpect(jsonPath("$.designation").value("Designation"));

        verify(rubriqueService, times(1)).saveRubrique(any(Rubrique.class));
    }

    @Test
    public void testGetAllRubriques() throws Exception {
        List<Rubrique> rubriques = new ArrayList<>();
        rubriques.add(new Rubrique());
        rubriques.add(new Rubrique());

        when(rubriqueService.getAllRubriques()).thenReturn(rubriques);

        mockMvc.perform(get("/rubrique/listAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(rubriqueService, times(1)).getAllRubriques();
    }
}
