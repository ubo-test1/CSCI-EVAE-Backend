package com.back.csaback.Services;

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
        rubrique.setId(1L);

        when(rubriqueRepository.save(any(Rubrique.class))).thenReturn(rubrique);

        Rubrique savedRubrique = rubriqueService.saveRubrique(rubrique);

        assertEquals(rubrique.getId(), savedRubrique.getId());
        verify(rubriqueRepository, times(1)).save(rubrique);
    }

    @Test
    public void testDeleteRubrique() {
        Long rubriqueId = 1L;

        rubriqueService.deleteRubrique(rubriqueId);

        verify(rubriqueRepository, times(1)).deleteById(rubriqueId);
    }

    @Test
    public void testGetRubrique() {
        Rubrique rubrique = new Rubrique();
        rubrique.setId(1L);

        when(rubriqueRepository.findById(any(Long.class))).thenReturn(Optional.of(rubrique));

        Optional<Rubrique> fetchedRubrique = rubriqueService.getRubrique(1L);

        assertTrue(fetchedRubrique.isPresent());
        assertEquals(rubrique.getId(), fetchedRubrique.get().getId());
        verify(rubriqueRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllRubriques() {
        List<Rubrique> rubriques = new ArrayList<>();
        rubriques.add(new Rubrique());
        rubriques.add(new Rubrique());

        when(rubriqueRepository.findAll()).thenReturn(rubriques);

        List<Rubrique> fetchedRubriques = rubriqueService.getAllRubriques();

        assertEquals(2, fetchedRubriques.size());
        verify(rubriqueRepository, times(1)).findAll();
    }
}
