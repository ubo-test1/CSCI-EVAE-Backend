package com.back.csaback.Services;

import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.Promotion;
import com.back.csaback.Models.PromotionId;
import com.back.csaback.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    public List<Promotion> getAllbyEnseignant(Enseignant enseignant){
        return promotionRepository.findAllByNoEnseignant(enseignant);
    }
    public Optional<Promotion> findById(PromotionId id){
        return  promotionRepository.findById(id);
    }
}
