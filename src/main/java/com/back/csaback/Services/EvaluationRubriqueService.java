package com.back.csaback.Services;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvaluationRubriqueService {

    @Autowired
    private RubriqueEvaluationRepository rubriqueEvaluationRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private RubriqueService rubriqueService;

    public EvaluationRubriqueService(RubriqueEvaluationRepository rubriqueEvaluationRepository) {
        this.rubriqueEvaluationRepository = rubriqueEvaluationRepository;
    }

    public RubriqueEvaluation attachRubriqueToEval(Integer evaluationId, Integer rubriqueId, String designation){

        //RubriqueEvaluation r = getByIdRubriqueAndIdEval(rubriqueId,evaluationId);
        //if(r!=null) return null;

        RubriqueEvaluation rubriqueEvaluation = new RubriqueEvaluation();
        Evaluation eval = evaluationService.findById(evaluationId);
        Rubrique rubrique = rubriqueService.findById(rubriqueId);

        List<RubriqueEvaluation> list = getRubriqueByEval(eval);
        int pos = list.size()+1;

        String etat = eval.getEtat();
        if (!etat.equals("ELA")) throw new IllegalStateException();


        if(rubrique ==null || eval == null) throw new NullPointerException();

        rubriqueEvaluation.setIdEvaluation(eval);
        rubriqueEvaluation.setIdRubrique(rubrique);
        rubriqueEvaluation.setOrdre((short)pos);
        rubriqueEvaluation.setDesignation(designation);

        return rubriqueEvaluationRepository.save(rubriqueEvaluation);
    }
    public RubriqueEvaluation attachRubriqueToEval(Integer evaluationId, Integer rubriqueId, short ordre) {
        return attachRubriqueToEval(evaluationId,rubriqueId,null);
    }


    public void detachRubriqueFromEval(Integer idRubriqueEvaluation){
        Optional<RubriqueEvaluation> rubriqueEvaluation = rubriqueEvaluationRepository.findById(idRubriqueEvaluation.longValue());
        if(rubriqueEvaluation.isEmpty()) throw new EntityNotFoundException();
        List<RubriqueEvaluation> rubriques = rubriqueEvaluationRepository.findByEvaluation(rubriqueEvaluation.get().getIdEvaluation());

        rubriques.remove(rubriqueEvaluation.get());
        for(RubriqueEvaluation rubrique : rubriques){
            rubrique.setOrdre((short) (rubriques.indexOf(rubrique)+1));
        }
        String etat = rubriqueEvaluation.get().getIdEvaluation().getEtat();
        if (!etat.equals("ELA")) throw new IllegalStateException();
        rubriqueEvaluationRepository.deleteById(idRubriqueEvaluation.longValue());
    }


    public RubriqueEvaluation ordonnerRubriqueInEval(Integer idRubEval, Integer ordre) {

        RubriqueEvaluation rubEval = getById(idRubEval);
        if(rubEval == null) throw new EntityNotFoundException();
        List<RubriqueEvaluation> rubriques = rubriqueEvaluationRepository.findByEvaluation(rubEval.getIdEvaluation());
        rubriques.sort(Comparator.comparingInt(RubriqueEvaluation::getOrdre));
        String etat = rubEval.getIdEvaluation().getEtat();
        if (!etat.equals("ELA")) throw new IllegalStateException();

        int target_order = ordre;

        List<RubriqueEvaluation> newList = moveObjectToPosition(rubriques,rubEval,target_order-1);
        rubriqueEvaluationRepository.saveAll(newList);
        return rubEval;
    }

    public List<RubriqueEvaluation> moveObjectToPosition(List<RubriqueEvaluation> list,RubriqueEvaluation r,int targetPosition){

        if (targetPosition < 0) {
            throw new IllegalArgumentException("Target position is out of bounds");
        }

        list.remove(r);
        int newPosition = Math.min(targetPosition, list.size());
        list.add(newPosition, r);

        for(RubriqueEvaluation rubrique : list){
            rubrique.setOrdre((short) (list.indexOf(rubrique)+1));
        }
        return list;
    }
    public List<RubriqueEvaluation> getAll(){
        return rubriqueEvaluationRepository.findAll();
    }

    public RubriqueEvaluation getById(Integer id){
        Optional<RubriqueEvaluation> r = rubriqueEvaluationRepository.findById(id.longValue());
        return r.orElse(null);
    }

    public List<RubriqueEvaluation> getByRubriqueAndEval(Rubrique rubrique,Evaluation evaluation){
        List<RubriqueEvaluation> r = rubriqueEvaluationRepository.findByIdRubriqueAndIdEval(rubrique,evaluation);
        return r;
    }

        public List<RubriqueEvaluation> getRubriqueByEval(Evaluation evaluation){
            List<RubriqueEvaluation> r = rubriqueEvaluationRepository.findByEvaluation(evaluation);
        return r;
    }
}
