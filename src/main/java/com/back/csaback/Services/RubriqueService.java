package com.back.csaback.Services;

import com.back.csaback.DTO.RubQRequest;
import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.Models.*;
import com.back.csaback.Repositories.*;
import com.back.csaback.DTO.RubriqueDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RubriqueService {

    @Autowired
    private RubriqueRepository rr;
    @Autowired
    private RubQuesRepository rqr;
    @Autowired
    private EvaRubRepository err;
    @Autowired
    private EvaluationRepository eer;
    @Autowired
    private RubriqueEvaluationRepository rer;
    @Autowired
    private QuestionRepository qr;
    @Autowired
    private QuestionEvaluationRepository qer;

    public List<Question> getRubQuest(Rubrique r) {
        try {
            List<RubriqueQuestion> lrq = rqr.findAllByIdRubrique(r);
            List<Question> ret = new ArrayList<>();
            for (RubriqueQuestion rq : lrq) {
                ret.add(rq.getIdQuestion());
            }

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RubriqueAssociated> getAllStd() {
        List<Rubrique> rubriques = rr.findAll();
        List<RubriqueAssociated> rubriqueAssociateds = new ArrayList<>();
        for (Rubrique rubrique : rubriques) {
            if (checkIfUsed(rubrique)) rubriqueAssociateds.add(new RubriqueAssociated(rubrique, true));
            else rubriqueAssociateds.add(new RubriqueAssociated(rubrique, false));
        }
        return rubriqueAssociateds;
    }

    public Rubrique createRubStd(Rubrique r) {
        if(rr.existsByDesignation(r.getDesignation())) throw new IllegalArgumentException("Il existe deja une rubrique avec la designation");
        r.setType("RBS");
        return rr.save(r);
    }


    public Rubrique createRubPers(Rubrique r) {
        try {
            if(rr.existsByDesignation(r.getDesignation())) throw new IllegalArgumentException("Il existe deja une rubrique avec la designation");
            r.setType("RBP");
            return rr.save(r);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkIfUsed(Rubrique r) {
        System.out.println(r.getId());
        return !err.findAllByIdRubrique(r).isEmpty();
    }

    public Rubrique updateRub(Rubrique r) {
        Rubrique rubrique = findById(Integer.parseInt(""+r.getId()));
        if(!r.getType().equals("RBS") && !r.getType().equals("RBP")) throw new IllegalArgumentException("Type invalide");
        System.out.println(((!Objects.equals(rubrique.getDesignation().toLowerCase(), r.getDesignation().toLowerCase()))));
        if (this.checkIfUsed(r)) throw new IllegalStateException("La rubrique est utilise dans une evaluation");
        if((!Objects.equals(rubrique.getDesignation().toLowerCase(), r.getDesignation().toLowerCase())) && (rr.existsByDesignation(r.getDesignation()))) throw new IllegalArgumentException("Il existe deja une rubrique avec la designation");
        rubrique.setType(r.getType());
        if(r.getDesignation() != null) rubrique.setDesignation(r.getDesignation());
        if(r.getOrdre() != null) rubrique.setOrdre(r.getOrdre());
        return rr.save(r);
    }


    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la rubrique ne peut pas être null.");
        }
        Rubrique rubrique = this.findById(id);
        if (checkIfUsed(rubrique)) {
            throw new IllegalStateException("La rubrique est utilise dans une evaluation");
        } else {
            rqr.deleteAll(rqr.findAllByIdRubrique(rubrique));
            rr.delete(rubrique);
        }
    }

    public RubriqueDetails consulterRubriqueComp(Rubrique r) {
        try {
            RubriqueDetails ret = new RubriqueDetails();
            List<Question> lq = new ArrayList<>(this.getRubQuest(r));
            ret.setRubrique(r);
            ret.setQuestions(lq);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Rubrique consulter(Integer id) {
        try {
            return rr.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isCompose(Rubrique r){
        return !rqr.findAllByIdRubrique(r).isEmpty();
    }

    public Rubrique findById(Integer id) {
        return rr.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La rubrique avec l'ID " + id + " n'existe pas"));
    }

    public Rubrique saveRubrique(Rubrique rubrique) {
        return rr.save(rubrique);
    }

    public void assignQ(Integer id, Question q){
        RubriqueQuestion rq = new RubriqueQuestion();
        RubriqueQuestionId rid = new RubriqueQuestionId();
        rid.setIdRubrique(id);
        rid.setIdQuestion(q.getId());
        rq.setId(rid);
        rq.setIdRubrique(rr.findById(id).get());
        rq.setIdQuestion(q);
        if(getLastOrdre(id) == null){
            rq.setOrdre(1L);
        }else{
            rq.setOrdre(getLastOrdre(id) + 1);
        }
        rqr.save(rq);
    }

    public Long getLastOrdre(Integer idRub){
        return rqr.findMaxOrdreByRubriqueId(idRub);
    }

    public void assignQList(RubQRequest req){
        rr.findById(req.getRubriqueId()).orElseThrow(() -> new IllegalArgumentException("La rubrique n existe pas"));
        for(Integer q : req.getQList()){
            if(!checkQAssigned(req.getRubriqueId(),qr.findById(q).orElseThrow(() -> new IllegalArgumentException("La question n existe pas")))) assignQ(req.getRubriqueId(),qr.findById(q).orElseThrow(() -> new IllegalArgumentException("La question n existe pas")));
        }
    }

    public Boolean checkQAssigned(Integer id, Question q){
        return rqr.findQuestionsByRubriqueId(id).contains(q);
    }

    public short getLastOrdreRE(Evaluation e){
        System.out.println("------------------------");
        System.out.println(rer.findLastOrdreByEvaluationId(e.getId()));
        if(rer.findLastOrdreByEvaluationId(e.getId())==null) return 1;
        return rer.findLastOrdreByEvaluationId(e.getId());
    }

    public void migrateQuestions(RubriqueEvaluation re, Rubrique r){
        List<Question> lq = rqr.findQuestionsByRubriqueId(r.getId());
        for(Question q : lq){
            QuestionEvaluation temp = new QuestionEvaluation();
            temp.setIdRubriqueEvaluation(re);
            temp.setIdQuestion(q);
            //temp.setIdQualificatif(q.getIdQualificatif());
            if(qer.findLatestOrdreByRubriqueEvaluationId(re.getIdRubrique().getId()) == null){
                temp.setOrdre((short) 1);
            }else{
                temp.setOrdre((short) (qer.findLatestOrdreByRubriqueEvaluationId(re.getIdRubrique().getId())+1));
            }
            //temp.setIntitule(q.getIntitule());
            qer.save(temp);
        }
    }

    public void deleteQuestionsRub(RubriqueEvaluation re){
        qer.deleteAllByIdRubriqueEvaluation(re.getId());
        rer.delete(re);
    }
}