package com.back.csaback.Services;

import com.back.csaback.DTO.RubriqueAssociated;
import com.back.csaback.Models.*;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.RubQuesRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Requests.RubriqueDetails;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Question> getRubQuest(Rubrique r){
        try{
            List<RubriqueQuestion> lrq = rqr.findAllByIdRubrique(r);
            List<Question> ret = new ArrayList<>();
            for(RubriqueQuestion rq : lrq){
                ret.add(rq.getIdQuestion());
            }

            return ret;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<RubriqueAssociated> getAllStd() {
        List<Rubrique> rubriques = rr.findAll();
        List<RubriqueAssociated> rubriqueAssociateds= new ArrayList<>();
        for (Rubrique rubrique:rubriques){
            if(checkIfUsed(rubrique)) rubriqueAssociateds.add(new RubriqueAssociated(rubrique,true));
            else rubriqueAssociateds.add(new RubriqueAssociated(rubrique,false));
        }
        return rubriqueAssociateds;
    }
    public Rubrique createRubStd(Rubrique r){
        try{
            r.setType("RBS");
            return rr.save(r);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkIfUsed(Rubrique r){
        System.out.println(r.getId());
        return !err.findAllByIdRubrique(r).isEmpty();
    }

    public Rubrique updateRub(Rubrique r){
        try{
            if(this.checkIfUsed(r)) throw new IllegalStateException("La rubrique est utilise dans une evaluation");
            return rr.save(r);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public void delete(Long id) {
        Rubrique rubrique=new Rubrique();
        try{
            rubrique=findById(id);
        }catch (EntityNotFoundException exc){throw exc;}
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la rubrique ne peut pas être null.");
        }
        if (checkIfUsed(rubrique)) {
            throw new IllegalStateException("La rubrique est utilise dans une evaluation");
        }else {
           rqr.deleteAll(rqr.findAllByIdRubrique(rubrique));
           rr.delete(rubrique);
        }
    }

    public RubriqueDetails consulterRubriqueComp(Rubrique r){
        try{
            RubriqueDetails ret = new RubriqueDetails();
            List<Question> lq = new ArrayList<>(this.getRubQuest(r));
            ret.setRubrique(r);
            ret.setQuestions(lq);
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Rubrique consulter(Long id){
        try{
            return rr.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Rubrique findById(Long id) {
        return rr.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La question avec l'ID " + id + " n'existe pas."));
    }

}
