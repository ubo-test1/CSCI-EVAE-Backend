package com.back.csaback.Services;

import com.back.csaback.Models.Rubrique;
import com.back.csaback.Repositories.RubriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RubriqueService {
    @Autowired
    private RubriqueRepository rubriqueRepository;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Rubrique saveRubrique(Rubrique rubrique) {
        return rubriqueRepository.save(rubrique);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRubrique(Long id) {
        rubriqueRepository.deleteById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<Rubrique> getRubrique(Long id) {
        return rubriqueRepository.findById(id);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Rubrique> getAllRubriques() {
        try {
            return rubriqueRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve all rubriques.");
        }
    }

}
