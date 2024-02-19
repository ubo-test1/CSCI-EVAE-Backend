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
    void testUpdateQualificatif_ThrowErrorQualificatifAssociated() {
        // Création d'un qualificatif fictif avec un ID existant
        Integer idQualificatif = 1;
        Qualificatif existingQualificatif = new Qualificatif(idQualificatif, "Ancien Minimal", "Ancien Maximal");

        // Simulation de la méthode findById du repository
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.of(existingQualificatif));

        // Création d'un nouveau qualificatif avec des valeurs modifiées
        Qualificatif newQualificatif = new Qualificatif(idQualificatif, "Nouveau Minimal", "Nouveau Maximal");

        // Simulation de la méthode existsByMinimalAndMaximal du repository (renvoie false car le nouveau qualificatif n'existe pas encore)
        when(qualificatifRepository.existsByMinimalAndMaximal(newQualificatif.getMinimal(), newQualificatif.getMaximal())).thenReturn(false);

        // Simulation de la méthode save du repository
        when(qualificatifRepository.save(existingQualificatif)).thenReturn(existingQualificatif);

        // Appel de la méthode à tester
        Qualificatif updatedQualificatif = qualificatifService.updateQualificatif(newQualificatif);

        // Vérification des résultats
        assertNotNull(updatedQualificatif);
        assertEquals(newQualificatif.getMinimal(), updatedQualificatif.getMinimal());
        assertEquals(newQualificatif.getMaximal(), updatedQualificatif.getMaximal());

        // Vérification des appels de méthodes sur les mocks
        //verify(qualificatifRepository, times(1)).findById(idQualificatif);
        verify(qualificatifRepository, times(1)).existsByMinimalAndMaximal(newQualificatif.getMinimal(), newQualificatif.getMaximal());
        verify(qualificatifRepository, times(1)).save(existingQualificatif);
    }

    @Test
    void testUpdateQualificatif_ThrowQualificatifExistException() {
        // Given
        Integer idQualificatif = 1;
        Qualificatif existingQualificatif = new Qualificatif(idQualificatif, "Minimal", "Maximal");
        Qualificatif newQualificatif = new Qualificatif(2, "New Minimal", "New Maximal");
        when(qualificatifRepository.findById(idQualificatif)).thenReturn(Optional.of(existingQualificatif));
        when(qualificatifRepository.existsByMinimalAndMaximal(newQualificatif.getMinimal(), newQualificatif.getMaximal())).thenReturn(true);

        // When / Then
        assertThrows(QualificatifExistException.class, () -> qualificatifService.updateQualificatif(newQualificatif));
    }
}
