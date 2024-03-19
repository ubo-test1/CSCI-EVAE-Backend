package com.back.csaback.Repositories;

import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.Promotion;
import com.back.csaback.Models.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, PromotionId> {
    public List<Promotion> findAllByNoEnseignant(Enseignant noEnseignant);
}