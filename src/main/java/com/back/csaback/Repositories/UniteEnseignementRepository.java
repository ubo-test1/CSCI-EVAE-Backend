package com.back.csaback.Repositories;

import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Models.UniteEnseignementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniteEnseignementRepository extends JpaRepository<UniteEnseignement, UniteEnseignementId> {
}