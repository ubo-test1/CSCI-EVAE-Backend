package com.back.csaback.Controllers;

import com.back.csaback.Config.Services.MD5PasswordEncoder;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import com.back.csaback.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
@CrossOrigin
public class TestController {
    private MD5PasswordEncoder pe;

    @Autowired
    private UserRepository ur;
    @Autowired
    private QuestionEvaluationRepository qer;

    @GetMapping("test")
    public String test(){
        return "Hello VVorld";
    }
/*
    @PostConstruct
    public void fixPasswords(){
        pe = new MD5PasswordEncoder();
        Authentification a1 = ur.findByLoginConnection("Administrateur").get();
        a1.setMotPasse(pe.encode(a1.getMotPasse()));
        ur.save(a1);
    }*/

    /*@PostConstruct
    public void fixQEV(){
        List<QuestionEvaluation> lqe = new ArrayList<>();
        lqe = qer.findAll();
        for(QuestionEvaluation q : lqe){
            int flg = 0;
            if(q.getIntitule() == null || q.getIntitule().isEmpty() ){
                q.setIntitule(q.getIdQuestion().getIntitule());
                flg = 1;
            }
            if(q.getIdQualificatif() == null){
                q.setIdQualificatif(q.getIdQuestion().getIdQualificatif());
                flg = 1;
            }
            if(flg == 1){
                qer.save(q);
            }

        }
    }*/
}