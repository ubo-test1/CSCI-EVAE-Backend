package com.back.csaback.Services;

import com.back.csaback.Exceptions.ErrorQualificatifAssociated;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Qualificatif;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.DTO.RubriqueDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Evaluation> findById(Integer id){

            return er.findById(id);

    }
    public Evaluation createEvaluation(Evaluation evaluation){
        return er.save(evaluation);
    }
    public Evaluation updateEvaluation(Evaluation q) throws Exception {
        if(er.findById(q.getId()).isEmpty()) throw new EntityNotFoundException("Evaluation n'existe pas");
        if(q.getEtat()=="CLO") throw new Exception("Cette evaluation est cloturée");
        return er.save(q);
    }
    public void deleteEvaluation(Evaluation evaluation) throws Exception {
                if(evaluation != null){
                er.delete(evaluation);
            }
            else {
                throw new IllegalArgumentException("L'evaluation avec l'ID " + evaluation + " n'existe pas.");
            }
        }
    }

