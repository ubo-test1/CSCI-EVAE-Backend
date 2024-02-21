package com.back.csaback.Repositories;

import com.back.csaback.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EvaluationRepositoryTest {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    ElementConstitutifRepository elementConstitutifRepository;
    @Autowired
    PromotionRepository promotionRepository;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    UniteEnseignementRepository uniteEnseignementRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllEvaluation() {
        Evaluation evaluation = new Evaluation();
        Enseignant enseignant = new Enseignant();
        enseignant.setType("type");
        enseignant.setSexe("M"); //
        enseignant.setNom("Nom");
        enseignant.setPrenom("Prénom");
        enseignant.setAdresse("Adresse");
        enseignant.setCodePostal("12345");
        enseignant.setVille("Ville");
        enseignant.setPays("Pays");
        enseignant.setMobile("0123456789");
        enseignant.setTelephone("0123456789");
        enseignant.setEmailUbo("email@ubo.fr");
        enseignant.setEmailPerso("email@perso.fr");
        Formation formation = new Formation();
        formation.setNomFormation("M2DOSI");
        formation.setDiplome("M");
        formation.setCodeFormation("M2DOSI");
        formationRepository.save(formation);
        evaluation.setNoEnseignant(enseignantRepository.save(enseignant));
        evaluation.setPromotion(new Promotion());
        ElementConstitutif elementConstitutif=new ElementConstitutif();
        elementConstitutif.setNoEnseignant(enseignant);
        elementConstitutif.setDesignation("test");
        UniteEnseignement uniteEnseignement = new UniteEnseignement();
        uniteEnseignement.setCodeFormation(formation);
        uniteEnseignement.setSemestre("9");
        uniteEnseignement.setDesignation("test");
        UniteEnseignementId  uniteEnseignementId = new UniteEnseignementId();
        uniteEnseignementId.setCodeUe("test");
        uniteEnseignementId.setCodeFormation("M2DOSI");
        uniteEnseignement.setNoEnseignant(enseignant);
        uniteEnseignement.setId(uniteEnseignementId);
        uniteEnseignementRepository.save(uniteEnseignement);
        elementConstitutif.setUniteEnseignement(uniteEnseignement);
        ElementConstitutifId elementConstitutifId = new ElementConstitutifId();
        elementConstitutifId.setCodeUe("test");
        elementConstitutifId.setCodeEc("test");
        elementConstitutifId.setCodeFormation("M2DOSI");
        elementConstitutif.setId(elementConstitutifId);
        evaluation.setElementConstitutif(elementConstitutifRepository.save(elementConstitutif));
        Promotion promotion=new Promotion();
        promotion.setNbMaxEtudiant((short) 24);
        PromotionId promotionId =new PromotionId();
        promotionId.setCodeFormation("M2DOSI");
        promotionId.setAnneeUniversitaire("2015-2016");
        promotion.setId(promotionId);
        promotion.setCodeFormation(formation);
        evaluation.setPromotion(promotionRepository.save(promotion));
        evaluation.setNoEvaluation((short) 1);
        evaluation.setDesignation("test");
        evaluation.setEtat("tes");
        evaluation.setDebutReponse(now());
        evaluation.setFinReponse(now());
        evaluation.setPeriode("test");
        evaluationRepository.save(evaluation);
        List<Evaluation> evaluations=evaluationRepository.findAll();
        assertEquals(1, evaluations.size());
    }

    @Test
    public void testFindByIdQuestion() {
        Evaluation evaluation = new Evaluation();
        evaluation.setId(2);
        Enseignant enseignant = new Enseignant();
        enseignant.setType("type");
        enseignant.setSexe("M"); //
        enseignant.setNom("Nom");
        enseignant.setPrenom("Prénom");
        enseignant.setAdresse("Adresse");
        enseignant.setCodePostal("12345");
        enseignant.setVille("Ville");
        enseignant.setPays("Pays");
        enseignant.setMobile("0123456789");
        enseignant.setTelephone("0123456789");
        enseignant.setEmailUbo("email@ubo.fr");
        enseignant.setEmailPerso("email@perso.fr");
        Formation formation = new Formation();
        formation.setNomFormation("M2DOSI");
        formation.setDiplome("M");
        formation.setCodeFormation("M2DOSI");
        formationRepository.save(formation);
        evaluation.setNoEnseignant(enseignantRepository.save(enseignant));
        evaluation.setPromotion(new Promotion());
        ElementConstitutif elementConstitutif=new ElementConstitutif();
        elementConstitutif.setNoEnseignant(enseignant);
        elementConstitutif.setDesignation("test");
        UniteEnseignement uniteEnseignement = new UniteEnseignement();
        uniteEnseignement.setCodeFormation(formation);
        uniteEnseignement.setSemestre("9");
        uniteEnseignement.setDesignation("test");
        UniteEnseignementId  uniteEnseignementId = new UniteEnseignementId();
        uniteEnseignementId.setCodeUe("test");
        uniteEnseignementId.setCodeFormation("M2DOSI");
        uniteEnseignement.setNoEnseignant(enseignant);
        uniteEnseignement.setId(uniteEnseignementId);
        uniteEnseignementRepository.save(uniteEnseignement);
        elementConstitutif.setUniteEnseignement(uniteEnseignement);
        ElementConstitutifId elementConstitutifId = new ElementConstitutifId();
        elementConstitutifId.setCodeUe("test");
        elementConstitutifId.setCodeEc("test");
        elementConstitutifId.setCodeFormation("M2DOSI");
        elementConstitutif.setId(elementConstitutifId);
        evaluation.setElementConstitutif(elementConstitutifRepository.save(elementConstitutif));
        Promotion promotion=new Promotion();
        promotion.setNbMaxEtudiant((short) 24);
        PromotionId promotionId =new PromotionId();
        promotionId.setCodeFormation("M2DOSI");
        promotionId.setAnneeUniversitaire("2015-2016");
        promotion.setId(promotionId);
        promotion.setCodeFormation(formation);
        evaluation.setPromotion(promotionRepository.save(promotion));
        evaluation.setNoEvaluation((short) 1);
        evaluation.setDesignation("test");
        evaluation.setEtat("tes");
        evaluation.setDebutReponse(now());
        evaluation.setFinReponse(now());
        evaluation.setPeriode("test");
        evaluationRepository.save(evaluation);
        Optional<Evaluation> evaluation1=evaluationRepository.findById(2);
        assertTrue(evaluation1.isPresent());
        assertEquals(evaluation.getDesignation(), evaluation1.get().getDesignation());
        assertEquals(evaluation.getPeriode(), evaluation1.get().getPeriode());
    }

}