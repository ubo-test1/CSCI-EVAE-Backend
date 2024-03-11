package com.back.csaback.Services;

import com.back.csaback.Models.*;
import com.back.csaback.Repositories.*;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.DTO.RubriqueDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    RubriqueEvaluationRepository rubriqueEvaluationRepository;
    @Autowired
    ReponseEvaluationRepository reponseEvaluationRepository;
    @Autowired
    QuestionEvaluationRepository questionEvaluationRepository;
    @Autowired
    private ReponseQuestionRepository reponseQuestionRepository;
    @Autowired
    private DroitRepository droitRepository;
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
            return er.findByCustomQuery(id).get();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Optional<Evaluation> findByIdOpt(Integer id){
        return er.findByCustomQuery(id);

    }

    public List<Evaluation> findAllByPromo(Etudiant e){
        return er.findAllByPromotionAndNotEtatELA(e.getPromotion());
    }

    public Boolean isClosedRep(Integer e){
        return !er.findById(e).get().getEtat().equals("DIS");
    }

    public Evaluation updateEvaluation(Evaluation q) throws Exception {
        if(er.findById(q.getId()).isEmpty()) throw new EntityNotFoundException("Evaluation n'existe pas");
        // if(q.getEtat()=="CLO") throw new Exception("Cette evaluation est cloturée");
        return createEvaluation(q);
    }
    public void deleteEvaluation(Integer id) throws Exception {
        Evaluation evaluation= findById(id);
        if(evaluation != null){
            if(Objects.equals(evaluation.getEtat(), "CLO"))  throw new IllegalArgumentException("Vous ne pouvez pas supprimer l'évaluation car elle est cloturée");
            reponseQuestionRepository.deleteAll(reponseQuestionRepository.findAllByIdReponseEvaluationIn(reponseEvaluationRepository.findAllByIdEvaluation(evaluation)));
            questionEvaluationRepository.deleteAll(questionEvaluationRepository.findAllByIdRubriqueEvaluationIn(rubriqueEvaluationRepository.findAllByIdEvaluation(evaluation)));
            reponseEvaluationRepository.deleteAll(reponseEvaluationRepository.findAllByIdEvaluation(evaluation));
            rubriqueEvaluationRepository.deleteAll(rubriqueEvaluationRepository.findAllByIdEvaluation(evaluation));
            droitRepository.deleteAll(droitRepository.findAllByIdEvaluation(evaluation));
            er.delete(evaluation);
        }
        else {
            throw new IllegalArgumentException("L'evaluation avec l'ID " + evaluation + " n'existe pas ");
        }
    }
    public Evaluation createEvaluation(Evaluation evaluation){
        if(promotionRepository.findById(evaluation.getPromotion().getId()).isEmpty() || evaluation.getPromotion().getId()==null) throw new EntityNotFoundException("La promotion choisie n'existe pas");
        if(uniteEnseignementRepository.findById(evaluation.getUniteEnseignement().getId()).isEmpty()| evaluation.getPromotion().getId()==null) throw new EntityNotFoundException("L'unité enseignement choisie n'existe pas");
        if(enseignantRepository.findById(evaluation.getNoEnseignant().getId()).isEmpty()| evaluation.getPromotion().getId()==null) throw new EntityNotFoundException("l'enseignant choisie n'existe pas");
        if (evaluation.getElementConstitutif()!=null && (elementConstitutifRepository.findById(evaluation.getElementConstitutif().getId()).isEmpty())) throw new EntityNotFoundException("l'element constitutif choisie n'existe pas");
        if(evaluation.getNoEvaluation()==null) evaluation.setNoEvaluation((short) (AttribuerNoEnseignant()+1));
        return er.save(evaluation);
    }
    Short AttribuerNoEnseignant(){if(er.findNoEvaluationMax()==null) return  1; else return  er.findNoEvaluationMax() ;}
}

