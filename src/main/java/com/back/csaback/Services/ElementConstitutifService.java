package com.back.csaback.Services;

import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.ElementConstitutifId;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Repositories.ElementConstitutifRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementConstitutifService {
    @Autowired
    ElementConstitutifRepository elementConstitutifRepository;

    public Optional<ElementConstitutif> findById(ElementConstitutifId id) {
        return elementConstitutifRepository.findById(id);
    }
    public List<ElementConstitutif> getAllByUeAndEnseigant(UniteEnseignement ue,Enseignant e){
        return elementConstitutifRepository.findElementConstitutifsByUniteEnseignementAndNoEnseignant(ue,e);
    }
}
