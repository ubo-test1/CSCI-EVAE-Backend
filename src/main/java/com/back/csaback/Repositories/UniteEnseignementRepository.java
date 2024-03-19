package com.back.csaback.Repositories;

import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Models.UniteEnseignementId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniteEnseignementRepository extends JpaRepository<UniteEnseignement, UniteEnseignementId> {
    List<UniteEnseignement>  findUniteEnseignementsByNoEnseignant(@NotNull Enseignant noEnseignant);

}