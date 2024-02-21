package com.back.csaback.Repositories;

import com.back.csaback.Models.Promotion;
import com.back.csaback.Models.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, PromotionId> {
}