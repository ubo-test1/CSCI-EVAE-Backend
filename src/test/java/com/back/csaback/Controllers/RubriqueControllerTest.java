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

        Rubrique r = new Rubrique();
        r.setType("type");
        r.setDesignation("designation");
        r.setOrdre(1L);

        Enseignant enseignant = new Enseignant();
        when(enseignantService.findById(1)).thenReturn(java.util.Optional.of(enseignant));

        Rubrique rubrique = new Rubrique();
        when(rubriqueService.saveRubrique(any(Rubrique.class))).thenReturn(rubrique);

        /*
        ResponseEntity<Rubrique> responseEntity = rubriqueController.createStd(r);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(rubrique, responseEntity.getBody());
        */
    }

    /*
    @Test
    public void testGetAllRubriques() {
        List<Rubrique> rubriqueList = new ArrayList<>();
        when(rubriqueService.getAllStd()).thenReturn(rubriqueList);

        ResponseEntity<List<Rubrique>> responseEntity = rubriqueController.getAllRubriques();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rubriqueList, responseEntity.getBody());
    }
    */
}
