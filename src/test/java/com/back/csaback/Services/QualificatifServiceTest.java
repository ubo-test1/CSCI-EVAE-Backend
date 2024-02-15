package com.back.csaback.Services;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QualificatifServiceTest {

    @Mock
    private QualificatifRepository qualificatifRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QualificatifService qualificatifService;

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
        qualificatifs.add(new Qualificatif(1L, "Minimal1", "Maximal1"));
        qualificatifs.add(new Qualificatif(2L, "Minimal2", "Maximal2"));
        when(qualificatifRepository.findAll()).thenReturn(qualificatifs);

        List<QualificatifAssociated> result = qualificatifService.GetAllQualificatifs();
        assertEquals(2, result.size());
    }

    @Test
    void testFindQualificationById_ThrowsEntityNotFoundException() {
        Long idQualificatif = 1L;
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> qualificatifService.findQualificationById(idQualificatif));
    }

    @Test
    void testFindQualificationById() {
        Long idQualificatif = 1L;
        Qualificatif qualificatif = new Qualificatif(idQualificatif, "Minimal", "Maximal");
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.of(qualificatif));
        assertEquals(qualificatif, qualificatifService.findQualificationById(idQualificatif));
    }

    @Test
    void testCreateQualificatif() {
        Qualificatif qualificatif = new Qualificatif(1L, "Minimal", "Maximal");
        when(qualificatifRepository.save(qualificatif)).thenReturn(qualificatif);
        assertEquals(qualificatif, qualificatifService.createQualificatif(qualificatif));
    }

    @Test
    void testDeleteQualificatif() {
        Long qualificatifId = 1L;
        Qualificatif qualificatif = new Qualificatif(qualificatifId, "Minimal", "Maximal");
        when(qualificatifRepository.findById(qualificatifId)).thenReturn(Optional.of(qualificatif));
        when(questionRepository.findAll()).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> qualificatifService.deleteQualificatif(qualificatifId));
        verify(qualificatifRepository, times(1)).delete(qualificatif);
    }

    /*@Test
    void testUpdateQualificatif_ThrowErrorQualificatifAssociated() {
        // Given
        Long idQualificatif = 1L;
        Qualificatif existingQualificatif = new Qualificatif(idQualificatif, "Minimal", "Maximal");
        Qualificatif newQualificatif = new Qualificatif(idQualificatif, "New Minimal", "New Maximal");
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.of(existingQualificatif));
        when(qualificatifService.isQualificatifAssociated(idQualificatif)).thenReturn(true);

        // When / Then
        assertThrows(ErrorQualificatifAssociated.class, () -> qualificatifService.updateQualificatif(idQualificatif, newQualificatif));
    }*/

    @Test
    void testUpdateQualificatif_ThrowQualificatifExistException() {
        // Given
        Long idQualificatif = 1L;
        Qualificatif existingQualificatif = new Qualificatif(idQualificatif, "Minimal", "Maximal");
        Qualificatif newQualificatif = new Qualificatif(2L, "New Minimal", "New Maximal");
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.of(existingQualificatif));
        when(qualificatifRepository.existsByMinimalAndMaximal(newQualificatif.getMinimal(), newQualificatif.getMaximal())).thenReturn(true);

        // When / Then
        assertThrows(QualificatifExistException.class, () -> qualificatifService.updateQualificatif(idQualificatif, newQualificatif));
    }
}
