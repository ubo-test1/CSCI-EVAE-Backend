package com.back.csaback.Repositories;

import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.ElementConstitutifId;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.UniteEnseignement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, ElementConstitutifId> {
    Optional<ElementConstitutif> findById(ElementConstitutifId id);
    List<ElementConstitutif> findElementConstitutifsByUniteEnseignementAndNoEnseignant(@NotNull UniteEnseignement uniteEnseignement, @NotNull Enseignant noEnseignant);
}