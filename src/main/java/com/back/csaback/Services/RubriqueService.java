package com.back.csaback.Services;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Repositories.EvaRubRepository;
import com.back.csaback.Repositories.RubQuesRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import com.back.csaback.Requests.RubriqueDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RubriqueService {

    @Autowired
    private RubriqueRepository rr;

    @Autowired
    private RubQuesRepository rqr;

    @Autowired
    private EvaRubRepository err;

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
}
