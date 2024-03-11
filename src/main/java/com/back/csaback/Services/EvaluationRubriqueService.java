package com.back.csaback.Services;

import com.back.csaback.Exceptions.ErrorEvaluationNoOuverte;
import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Exceptions.ErrorRubriqueEvaluationAlreadyExist;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvaluationRubriqueService {

    @Autowired
    private RubriqueEvaluationRepository rubriqueEvaluationRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private RubriqueRepository rubriqueRepository;
    @Autowired
    private QuestionRepository questionRepository;






    public RubriqueEvaluation save(RubriqueEvaluation newRubriqueEvaluation) {
        if(evaluationRepository.findByCustomQuery(newRubriqueEvaluation.getIdEvaluation().getId()).isEmpty() ) throw new EntityNotFoundException("L'evaluation choisie n'existe pas !");
        if (!Evaluation_EncoursElaboration(newRubriqueEvaluation.getIdEvaluation().getId())) throw new ErrorEvaluationNoOuverte("l'evaluation n'est pas en cours d'elaboration");
        if(rubriqueRepository.findById(newRubriqueEvaluation.getIdRubrique().getId()).isEmpty() ) throw new EntityNotFoundException("La rubrique choisie n'existe pas !");
        if (rubriqueEvaluationRepository.existsRubriqueEvaluationByIdRubriqueAndIdEvaluation(newRubriqueEvaluation.getIdRubrique(),newRubriqueEvaluation.getIdEvaluation())) throw new ErrorRubriqueEvaluationAlreadyExist("La rubrique choisie existe deéja !!");

        else   return rubriqueEvaluationRepository.save(newRubriqueEvaluation);
    }
    /*
    public void detachRubriqueFromEval(Integer idRubriqueEvaluation){
        Optional<RubriqueEvaluation> rubriqueEvaluation = rubriqueEvaluationRepository.findById(idRubriqueEvaluation);
        if(rubriqueEvaluation.isEmpty()) throw new EntityNotFoundException();
        List<RubriqueEvaluation> rubriques = rubriqueEvaluationRepository.findByEvaluation(rubriqueEvaluation.get().getIdEvaluation());
        rubriques.sort(Comparator.comparingInt(RubriqueEvaluation::getOrdre));
        String etat = rubriqueEvaluation.get().getIdEvaluation().getEtat();
        if (!etat.equals("ELA")) throw new IllegalStateException();
        rubriques.remove(rubriqueEvaluation.get());
        rubriqueEvaluationRepository.deleteById(idRubriqueEvaluation);

        for(RubriqueEvaluation rubrique : rubriques){
            rubrique.setOrdre((short) (rubriques.indexOf(rubrique)+1));
        }
        rubriqueEvaluationRepository.saveAll(rubriques);
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
        Optional<RubriqueEvaluation> r = rubriqueEvaluationRepository.findById(id);
        return r.orElse(null);
    }

    public List<RubriqueEvaluation> getByRubriqueAndEval(Rubrique rubrique,Evaluation evaluation){
        List<RubriqueEvaluation> r = rubriqueEvaluationRepository.findByIdRubriqueAndIdEval(rubrique,evaluation);
        return r;
    }

        public List<RubriqueEvaluation> getRubriqueByEval(Evaluation evaluation){
            List<RubriqueEvaluation> r = rubriqueEvaluationRepository.findByEvaluation(evaluation);
        return r;
    }*/
    public void delete(Integer id) {
        RubriqueEvaluation rubriqueToDelete =findById(id);
        rubriqueEvaluationRepository.delete(rubriqueToDelete);

    }

    public boolean Evaluation_EncoursElaboration(Integer id){
        return Objects.equals(evaluationRepository.findByCustomQuery(id).get().getEtat(), "ELA");
    }
    public RubriqueEvaluation findById(Integer id) {
        return rubriqueEvaluationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La rubrique evaluation avec l'ID " + id + " n'existe pas."));
    }

    public List<RubriqueEvaluation>  getAll(){return rubriqueEvaluationRepository.findAll(); }

    public RubriqueEvaluation update(RubriqueEvaluation rubriqueEvaluation) {
        //findById(questionEvaluation.getId());
        return rubriqueEvaluationRepository.save(rubriqueEvaluation);
    }

    public List<RubriqueEvaluation> findAllByIdEvaluation(Integer id){
        Optional<Evaluation> evaluation= evaluationRepository.findByCustomQuery(id);
        if(evaluation.isEmpty()) throw new EntityNotFoundException("Evaluation non trouvée !!");
        return rubriqueEvaluationRepository.findAllByIdEvaluation(evaluation.get());
    }
}
