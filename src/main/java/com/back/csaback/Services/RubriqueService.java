package com.back.csaback.Services;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Repositories.RubQuesRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RubriqueService {

    @Autowired
    private RubriqueRepository rr;

    @Autowired
    private RubQuesRepository rqr;

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

}
