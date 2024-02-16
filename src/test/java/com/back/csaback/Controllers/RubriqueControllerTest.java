package com.back.csaback.Controllers;

import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Services.EnseignantService;
import com.back.csaback.Services.RubriqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RubriqueControllerTest {

    @Mock
    private RubriqueService rubriqueService;

    @Mock
    private EnseignantService enseignantService;

    @InjectMocks
    private RubriqueController rubriqueController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveRubrique() {
        HashMap<String, String> request = new HashMap<>();
        request.put("id", "1");
        request.put("type", "type");
        request.put("designation", "designation");
        request.put("noEnseignant", "1");
        request.put("ordre", "1.0");

        Enseignant enseignant = new Enseignant();
        when(enseignantService.findById(1L)).thenReturn(java.util.Optional.of(enseignant));

        Rubrique rubrique = new Rubrique();
        when(rubriqueService.saveRubrique(any(Rubrique.class))).thenReturn(rubrique);

        ResponseEntity<Rubrique> responseEntity = rubriqueController.saveRubrique(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(rubrique, responseEntity.getBody());
    }

    @Test
    public void testGetAllRubriques() {
        List<Rubrique> rubriqueList = new ArrayList<>();
        when(rubriqueService.getAllRubriques()).thenReturn(rubriqueList);

        ResponseEntity<List<Rubrique>> responseEntity = rubriqueController.getAllRubriques();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rubriqueList, responseEntity.getBody());
    }
}
