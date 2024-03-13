package com.back.csaback.Services;

import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Repositories.UniteEnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniteEnseignemantService {
    @Autowired
    UniteEnseignementRepository uniteEnseignementRepository;
    public List<UniteEnseignement> getAllByEnseignant(Enseignant e){
        return uniteEnseignementRepository.findUniteEnseignementsByNoEnseignant(e);
    }
}
