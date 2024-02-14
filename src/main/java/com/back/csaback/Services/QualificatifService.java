//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.back.csaback.Services;

import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Repositories.QualificatifRepository;
import com.back.csaback.Repositories.QuestionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public List<Qualificatif> GetAllQualificatifs() {
        return this.qualificatifRepository.findAll();
    }

    public Qualificatif findQualificationById(Long idQualificatif) {
        Optional<Qualificatif> qualificatifOptional = this.qualificatifRepository.findById(idQualificatif);
        return (Qualificatif)qualificatifOptional.orElseThrow(() -> {
            return new RuntimeException("Le couple qualificatif avec l'ID spécifié n'a pas été trouvé.");
        });
    }

    public Qualificatif createQualificatif(Qualificatif qualificatif) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                throw new RuntimeException("Seul l'administrateur est autorisé à créer des couples qualificatifs.");
            }
        }

        return (Qualificatif)this.qualificatifRepository.save(qualificatif);
    }

    public void deleteQualificatif(Long qualificatifId) {
        if (this.questionRepository.existsByQualificatifId(qualificatifId)) {
            throw new RuntimeException("Le couple qualificatif est déjà utilisé dans une question dans une rubrique et ne peut pas être supprimé.");
        } else {
            this.qualificatifRepository.deleteById(qualificatifId);
        }
    }

    public Qualificatif updateQualificatifById(Qualificatif qualificatif) {
        Qualificatif existingQualificatif = (Qualificatif)this.qualificatifRepository.findById(qualificatif.getIdQualificatif()).orElseThrow(() -> {
            return new RuntimeException("Le couple qualificatif avec l'ID spécifié n'a pas été trouvé.");
        });
        if (this.questionRepository.existsByQualificatifId(qualificatif.getIdQualificatif())) {
            throw new RuntimeException("Le couple qualificatif est déjà utilisé dans une question dans une rubrique et ne peut pas être modifié.");
        } else {
            existingQualificatif.setMaximal(qualificatif.getMaximal());
            existingQualificatif.setMinimal(qualificatif.getMinimal());
            return (Qualificatif)this.qualificatifRepository.save(existingQualificatif);
        }
    }
}
