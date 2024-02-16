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
        List<QualificatifAssociated> qualificatifAssociateds= new ArrayList<>();
        for (Qualificatif qualificatif:qualificatifs){
            QualificatifAssociated qualificatifAssociated = new QualificatifAssociated();
            if(isQualificatifAssociated(qualificatif.getIdQualificatif())) qualificatifAssociated.setAssociated(true);
            else qualificatifAssociated.setAssociated(false);
            qualificatifAssociated.setQualificatif(qualificatif);
            qualificatifAssociateds.add(qualificatifAssociated);
        }
        return qualificatifAssociateds;
    }

    public Qualificatif findQualificationById(Long idQualificatif) {
        return qualificatifRepository.findById(idQualificatif)
                .orElseThrow(() -> new EntityNotFoundException("Le couple qualificatif avec l'ID" + idQualificatif + " n'a pas été trouvé."));
    }

    public Qualificatif createQualificatif(Qualificatif qualificatif) {
        return qualificatifRepository.save(qualificatif);
    }



    public void deleteQualificatif(Long qualificatifId) {
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




    public Qualificatif updateQualificatif(Long idQualificatif, Qualificatif newQualificatif) {
        // Vérifier si le couple qualificatif existe dans la table Question
        Qualificatif qualificatif = new Qualificatif();
        try{
            qualificatif = findQualificationById(idQualificatif);
        }
        catch(EntityNotFoundException exc){

        }

        if (isQualificatifAssociated(idQualificatif)) {
            throw new ErrorQualificatifAssociated("Le couple qualificatif est déjà utilisé dans une question et ne peut pas être mis à jour.");
        }


        // Vérifier si le nouveau couple qualificatif existe déjà dans la base de données avec un autre ID
        String newMinimal = newQualificatif.getMinimal();
        String newMaximal = newQualificatif.getMaximal();
        boolean existingQualificatif = qualificatifRepository.existsByMinimalAndMaximal(newMinimal, newMaximal);
        if (existingQualificatif) {
            throw new QualificatifExistException("Un autre couple qualificatif avec les mêmes valeurs minimal et maximal existe déjà.");
        }

        Optional<Qualificatif> qualificatifOptional = qualificatifRepository.findById(idQualificatif);
        Qualificatif qualificatifToUpdate = qualificatifOptional.orElse(null);

        if (qualificatifToUpdate == null) {
            throw new IllegalArgumentException("L'ID du qualificatif ne peut pas être null.");
        }
        // Mettre à jour les valeurs du couple qualificatif
        qualificatifToUpdate.setMinimal(newQualificatif.getMinimal());
        qualificatifToUpdate.setMaximal(newQualificatif.getMaximal());

        // Enregistrer et retourner le couple qualificatif mis à jour
        return qualificatifRepository.save(qualificatifToUpdate);
    }

    public boolean isQualificatifAssociated(Long id){
        List<Question>  questions =questionRepository.findAll();
        for (Question q:questions){
            if (Objects.equals(q.getIdQualificatif().getIdQualificatif(), id)) return true;
        }
        return false;
    }
}
