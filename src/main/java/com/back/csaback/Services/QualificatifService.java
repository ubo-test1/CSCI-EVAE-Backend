//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Services;

import com.back.csaback.DTO.QualificatifAssociated;
import com.back.csaback.Exceptions.ErrorQualificatifAssociated;
import com.back.csaback.Exceptions.QualificatifExistException;
import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Models.Question;
import com.back.csaback.Repositories.QualificatifRepository;
import com.back.csaback.Repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualificatifService {
    private final QualificatifRepository qualificatifRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QualificatifService(QualificatifRepository qualificatifRepository, QuestionRepository questionRepository) {
        this.qualificatifRepository = qualificatifRepository;
        this.questionRepository = questionRepository;
    }


    public boolean isQualificatifExists(String minimal, String maximal) {
        return qualificatifRepository.existsByMinimalAndMaximal(minimal, maximal);
    }

    public List<QualificatifAssociated> GetAllQualificatifs() {
        List<Qualificatif> qualificatifs = qualificatifRepository.findAll();
        if(qualificatifs.isEmpty()) throw new IllegalArgumentException("Pas de qualificatifs");
        List<QualificatifAssociated> qualificatifAssociateds= new ArrayList<>();
        for (Qualificatif qualificatif:qualificatifs){
            QualificatifAssociated qualificatifAssociated = new QualificatifAssociated();
            if(isQualificatifAssociated(qualificatif.getId())) qualificatifAssociated.setAssociated(true);
            qualificatifAssociated.setQualificatif(qualificatif);
            qualificatifAssociateds.add(qualificatifAssociated);
        }
        return qualificatifAssociateds;
    }

    public Qualificatif findQualificationById(Integer idQualificatif) {
        return qualificatifRepository.findById(idQualificatif)
                .orElseThrow(() -> new EntityNotFoundException("Le couple qualificatif avec l'ID" + idQualificatif + " n'a pas été trouvé."));
    }

    public Qualificatif createQualificatif(Qualificatif qualificatif) {
        return qualificatifRepository.save(qualificatif);
    }



    public void deleteQualificatif(Integer qualificatifId) {
        if (isQualificatifAssociated(qualificatifId)) {
            throw new ErrorQualificatifAssociated("Le couple qualificatif est déjà utilisé dans une question et ne peut pas être supprimé.");
        } else {
            Qualificatif q = findQualificationById(qualificatifId);
            if(q != null){
                qualificatifRepository.delete(q);
            }
            else {
                throw new IllegalArgumentException("Le qualificatif avec l'ID " + qualificatifId + " n'existe pas.");
            }
        }
    }




    public Qualificatif updateQualificatif(Qualificatif q) {
        if(qualificatifRepository.findById(q.getId()).isEmpty()) throw new EntityNotFoundException("Qualificatif n'existe pas");
        if(this.isQualificatifAssociated(q.getId())) throw new ErrorQualificatifAssociated("Qualificatif associe a une question deja");

        return qualificatifRepository.save(q);
    }

    public boolean isQualificatifAssociated(Integer id){
        List<Question> questions =questionRepository.findAll();
        for (Question q:questions){
            if (Objects.equals(q.getIdQualificatif().getId(), id)) return true;
        }
        return false;
    }
}
