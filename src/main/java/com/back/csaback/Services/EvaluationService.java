package com.back.csaback.Services;

import com.back.csaback.Models.*;
import com.back.csaback.Repositories.*;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.DTO.RubriqueDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository er;

    @Autowired
    private RubriqueRepository rr;

    @Autowired
    private EvaRubRepository err;

    @Autowired
    private RubriqueService rs;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private UniteEnseignementRepository uniteEnseignementRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    ElementConstitutifRepository elementConstitutifRepository;
    public List<Evaluation> getAll() {
        try {
            return er.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Rubrique> getEvaRub(Evaluation eva){
        try{
            List<Rubrique> ret = new ArrayList<>();
            List<RubriqueEvaluation> lre = err.findAllByIdEvaluation(eva);

            for(RubriqueEvaluation q : lre){
                ret.add(q.getIdRubrique());
            }

            return ret;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public EvaluationDetails ConsulterEvaluation(Evaluation evaluation){
        try{
            EvaluationDetails ret = new EvaluationDetails();
            ret.setEvaluation(evaluation);
            List<RubriqueDetails> lrd = new ArrayList<>();
            RubriqueDetails tmp;
            for(Rubrique r : this.getEvaRub(evaluation)){
                tmp = new RubriqueDetails();
                tmp.setRubrique(r);
                tmp.setQuestions(rs.getRubQuest(r));
                lrd.add(tmp);
            }
            ret.setRubriques(lrd);
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Evaluation findById(Integer id){
        try{
            return er.findById(id).get();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Optional<Evaluation> findByIdOpt(Integer id){
        return er.findById(id);

    }


    public List<Evaluation> findAllByPromo(Etudiant e){
        return er.findAllByPromotionAndNotEtatELA(e.getPromotion());
    }
    public Evaluation updateEvaluation(Evaluation q) throws Exception {
        if(er.findById(q.getId()).isEmpty()) throw new EntityNotFoundException("Evaluation n'existe pas");
        // if(q.getEtat()=="CLO") throw new Exception("Cette evaluation est cloturée");
        return createEvaluation(q);
    }
    public void deleteEvaluation(Evaluation evaluation) throws Exception {
        if(evaluation != null){
            if(Objects.equals(evaluation.getEtat(), "CLO"))  throw new IllegalArgumentException("Vous ne pouvez pas supprimer l'évaluation car elle est cloturée");
            er.delete(evaluation);
        }
        else {
            throw new IllegalArgumentException("L'evaluation avec l'ID " + evaluation + " n'existe pas.");
        }
    }
    public Evaluation createEvaluation(Evaluation evaluation){
        if(promotionRepository.findById(evaluation.getPromotion().getId()).isEmpty()) throw new EntityNotFoundException("La promotion choisie n'existe pas");
        if(uniteEnseignementRepository.findById(evaluation.getUniteEnseignement().getId()).isEmpty()) throw new EntityNotFoundException("L'unité enseignement choisie n'existe pas");
        if(enseignantRepository.findById(evaluation.getNoEnseignant().getId()).isEmpty()) throw new EntityNotFoundException("l'enseignant choisie n'existe pas");
        if (evaluation.getElementConstitutif()!=null && (elementConstitutifRepository.findById(evaluation.getElementConstitutif().getId()).isEmpty())) throw new EntityNotFoundException("l'element constitutif choisie n'existe pas");
        if(evaluation.getNoEvaluation()==null) evaluation.setNoEvaluation((short) (AttribuerNoEnseignant()+1));
        return er.save(evaluation);
    }
    Short AttribuerNoEnseignant(){if(er.findNoEvaluationMax()==null) return  1; else return  er.findNoEvaluationMax() ;}
}




