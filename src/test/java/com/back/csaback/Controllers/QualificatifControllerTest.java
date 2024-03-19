package com.back.csaback.Controllers;

import com.back.csaback.Exceptions.ErrorQualificatifAssociated;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.back.csaback.DTO.QualificatifAssociated;
import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Services.QualificatifService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.*;
import static org.mockito.Mockito.*;

class QualificatifControllerTest {
    @Mock
    private QualificatifService qualificatifService;

    @InjectMocks
    private QualificatifController qualificatifController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQualificatifs() {
        List<QualificatifAssociated> qualificatifs = new ArrayList<>();
        // Initialisation de la liste de qualificatifs si nécessaire
        when(qualificatifService.GetAllQualificatifs()).thenReturn(qualificatifs);
        ResponseEntity<List<QualificatifAssociated>> response = qualificatifController.getAllQualificatifs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(qualificatifs, response.getBody());
        verify(qualificatifService, times(1)).GetAllQualificatifs();
    }

    @Test
    void testCreateQualificatif() {
        Qualificatif qualificatif = new Qualificatif();
        when(qualificatifService.createQualificatif(qualificatif)).thenReturn(qualificatif);
        ResponseEntity<?> response = qualificatifController.createQualificatif(qualificatif);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(qualificatifService, times(1)).createQualificatif(qualificatif);
    }

    @Test
    void testDeleteQualificatif() {
        Integer idQualificatif = 1;
        ResponseEntity<?> responseEntity = qualificatifController.deleteQualificatif(idQualificatif);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(qualificatifService, times(1)).deleteQualificatif(idQualificatif);
    }

    @Test
    void testGetQualificatifById() {
        Integer idQualificatif = 1;
        Qualificatif qualificatif = new Qualificatif();
        when(qualificatifService.findQualificationById(idQualificatif)).thenReturn(qualificatif);
        ResponseEntity<Qualificatif> response = qualificatifController.getQualificatifById(idQualificatif);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(qualificatif, response.getBody());
        verify(qualificatifService, times(1)).findQualificationById(idQualificatif);
    }

    @Test
    void testUpdateQualificatif() {
        Integer idQualificatif = 1;
        Qualificatif qualificatif = new Qualificatif();
        BindingResult bindingResult = mock(BindingResult.class); // Mock BindingResult
        when(bindingResult.hasErrors()).thenReturn(false); // Mock hasErrors() to return false
        Qualificatif updatedQualificatif = new Qualificatif();
        when(qualificatifService.updateQualificatif(qualificatif)).thenReturn(updatedQualificatif);
        ResponseEntity<?> responseEntity = qualificatifController.updateQualificatif(qualificatif, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(qualificatifService, times(1)).updateQualificatif(qualificatif);
    }

    @Test
    void testDeleteQualificatifError() {
        Integer idQualificatif = 1;
        doThrow(ErrorQualificatifAssociated.class).when(qualificatifService).deleteQualificatif(idQualificatif);
        ResponseEntity<?> responseEntity = qualificatifController.deleteQualificatif(idQualificatif);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        verify(qualificatifService, times(1)).deleteQualificatif(idQualificatif);
    }

}