package com.back.csaback.Services;

import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Services.RubriqueService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class RubriqueServiceTest {

    @Mock
    private RubriqueRepository rubriqueRepository;

    @InjectMocks
    private RubriqueService rubriqueService;

    @Test
    public void testSaveRubrique() {
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        when(rubriqueRepository.save(any(Rubrique.class))).thenReturn(rubrique);

        Rubrique savedRubrique = rubriqueService.saveRubrique(rubrique);

        assertEquals(rubrique.getId(), savedRubrique.getId());
        verify(rubriqueRepository, times(1)).save(rubrique);
    }

    @Test
    public void testDeleteRubrique() {
        Integer rubriqueId = 1;

        rubriqueService.delete(rubriqueId);

        verify(rubriqueRepository, times(1)).deleteById(rubriqueId);
    }

    @Test
    public void testGetRubrique() {
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1);

        when(rubriqueRepository.findById(any(Integer.class))).thenReturn(Optional.of(rubrique));

        Optional<Rubrique> fetchedRubrique = rubriqueRepository.findById(1);

        assertTrue(fetchedRubrique.isPresent());
        assertEquals(rubrique.getId(), fetchedRubrique.get().getId());
        verify(rubriqueRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllRubriques() {
        List<Rubrique> rubriques = new ArrayList<>();
        rubriques.add(new Rubrique());
        rubriques.add(new Rubrique());

        when(rubriqueRepository.findAll()).thenReturn(rubriques);

        List<RubriqueAssociated> fetchedRubriques = rubriqueService.getAllStd();

        assertEquals(2, fetchedRubriques.size());
        verify(rubriqueRepository, times(1)).findAll();
    }
}
