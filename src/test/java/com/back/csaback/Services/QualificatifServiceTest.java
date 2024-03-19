package com.back.csaback.Services;

import com.back.csaback.Controllers.QualificatifController;
import com.back.csaback.DTO.QualificatifAssociated;
import com.back.csaback.Exceptions.ErrorQualificatifAssociated;
import com.back.csaback.Exceptions.QualificatifExistException;
import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Repositories.QualificatifRepository;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Services.QualificatifService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QualificatifServiceTest {

    @Mock
    QuestionRepository mockDAO;
    @Mock
    private QualificatifRepository qualificatifRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QualificatifService qualificatifService;

    @InjectMocks
    private QualificatifController qualificatifController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsQualificatifExists() {
        when(qualificatifRepository.existsByMinimalAndMaximal("Minimal", "Maximal")).thenReturn(true);
        assertTrue(qualificatifService.isQualificatifExists("Minimal", "Maximal"));
    }

    @Test
    void testGetAllQualificatifs() {
        List<Qualificatif> qualificatifs = new ArrayList<>();

        Qualificatif q1 = new Qualificatif();
        q1.setId(1);
        q1.setMinimal("mini1");
        q1.setMaximal("max2");
        Qualificatif q2 = new Qualificatif();
        q2.setId(2);
        q2.setMinimal("mini2");
        q2.setMaximal("max2");

        qualificatifs.add(q1);
        qualificatifs.add(q2);

        when(qualificatifRepository.findAll()).thenReturn(qualificatifs);

        List<QualificatifAssociated> result = qualificatifService.GetAllQualificatifs();
        assertEquals(2, result.size());
    }

    @Test
    void testFindQualificationById_ThrowsEntityNotFoundException() {
        Integer idQualificatif = 1;
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> qualificatifService.findQualificationById(idQualificatif));
    }

    @Test
    void testFindQualificationById() {
        Integer idQualificatif = 1;
        Qualificatif q = new Qualificatif();
        q.setId(idQualificatif);
        q.setMaximal("max");
        q.setMinimal("min");
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.of(q));
        assertEquals(q, qualificatifService.findQualificationById(idQualificatif));
    }

    @Test
    void testCreateQualificatif() {
        Qualificatif qualificatif = new Qualificatif(1, "Minimal", "Maximal");
        when(qualificatifRepository.save(qualificatif)).thenReturn(qualificatif);
        assertEquals(qualificatif, qualificatifService.createQualificatif(qualificatif));
    }

    @Test
    void testDeleteQualificatif() {
        Integer qualificatifId = 1;
        Qualificatif qualificatif = new Qualificatif(qualificatifId, "Minimal", "Maximal");
        when(qualificatifRepository.findById(qualificatifId)).thenReturn(Optional.of(qualificatif));
        when(questionRepository.findAll()).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> qualificatifService.deleteQualificatif(qualificatifId));
        verify(qualificatifRepository, times(1)).delete(qualificatif);
    }



    @Test
    public void testUpdateQualificatif() {
        // Given
        Qualificatif qualificatif = new Qualificatif();
        qualificatif.setId(1);

        // Mock du comportement du repository
        when(qualificatifRepository.findById(qualificatif.getId())).thenReturn(Optional.of(qualificatif));
        when(qualificatifRepository.save(qualificatif)).thenReturn(qualificatif);

        // When
        Qualificatif updatedQualificatif = qualificatifService.updateQualificatif(qualificatif);

        // Then
        assertNotNull(updatedQualificatif);
        assertEquals(qualificatif.getId(), updatedQualificatif.getId());
        verify(qualificatifRepository, times(1)).findById(qualificatif.getId());
        verify(qualificatifRepository, times(1)).save(qualificatif);
    }


    @Test
    public void testUpdateQualificatifQualificatifNotFound() {
        // Given
        Qualificatif qualificatif = new Qualificatif();
        qualificatif.setId(1);

        // Mock du comportement du repository
        when(qualificatifRepository.findById(qualificatif.getId())).thenReturn(Optional.empty());

        // When
        assertThrows(EntityNotFoundException.class, () -> qualificatifService.updateQualificatif(qualificatif));

        // Then
        verify(qualificatifRepository, times(1)).findById(qualificatif.getId());
        verify(qualificatifRepository, never()).save(qualificatif);
    }


}
