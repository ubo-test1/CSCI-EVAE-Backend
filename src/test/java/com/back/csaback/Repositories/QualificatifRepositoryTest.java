package com.back.csaback.Repositories;

import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Services.QualificatifService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;


class QualificatifRepositoryTest {

    @Mock
    private QualificatifRepository qualificatifRepository;

    @InjectMocks
    private QualificatifService qualificatifService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        qualificatifService = Mockito.mock(QualificatifService.class);
    }

    @Test
    void testSaveAndFindById() {
        Qualificatif qualificatif = new Qualificatif();
        qualificatif.setMinimal("Minimal");
        qualificatif.setMaximal("Maximal");
        Qualificatif savedQualificatif = qualificatifRepository.save(qualificatif);

        Optional<Qualificatif> foundQualificatif = qualificatifRepository.findById(savedQualificatif.getIdQualificatif());
        assertTrue(foundQualificatif.isPresent());
        assertEquals("Minimal", foundQualificatif.get().getMinimal());
        assertEquals("Maximal", foundQualificatif.get().getMaximal());
    }

    @Test
    void testFindAll() {
        List<Qualificatif> qualificatifs = qualificatifRepository.findAll();
        // Vérifiez si la liste des qualificatifs n'est pas vide ou si elle contient les éléments attendus
        assertFalse(qualificatifs.isEmpty());
        // Vérifiez d'autres conditions si nécessaire
    }

    @Test
    public void testDelete() {

        Qualificatif qualificatifToDelete = new Qualificatif();
        qualificatifToDelete.setMinimal("Minimal Value");
        qualificatifToDelete.setMaximal("Maximal Value");
        qualificatifRepository.save(qualificatifToDelete);


        qualificatifRepository.delete(qualificatifToDelete);


        assertFalse(qualificatifRepository.existsByMinimalAndMaximal("Minimal Value", "Maximal Value"));
    }

    @Test
    void testFindByMinimalAndMaximal() {

        // Given
        String minimal = "Minimal";
        String maximal = "Maximal";
        Qualificatif qualificatif = new Qualificatif(1L, minimal, maximal);

        Mockito.when(qualificatifRepository.existsByMinimalAndMaximal(minimal, maximal)).thenReturn(true);

        // When
        boolean result = qualificatifRepository.existsByMinimalAndMaximal(minimal, maximal);

        // Then
        Assertions.assertTrue(result);
    }
    }


