package com.back.csaback.Services;

import com.back.csaback.Models.Rubrique;
import com.back.csaback.Repositories.RubriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RubriqueService {
    @Autowired
    private RubriqueRepository rubriqueRepository;

    public Rubrique saveRubrique(Rubrique rubrique) {
        return rubriqueRepository.save(rubrique);
    }

    public void deleteRubrique(Long id) {
        rubriqueRepository.deleteById(id);
    }

    public Optional<Rubrique> getRubrique(Long id) {
        return rubriqueRepository.findById(id);
    }

    public List<Rubrique> getAllRubriques() {
        return rubriqueRepository.findAll();
    }

}
