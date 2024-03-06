package com.back.csaback.Services;


import com.back.csaback.Models.Etudiant;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.ReponseEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService {

    @Autowired
    private ReponseEvaluationRepository rer;

    @Autowired
    private EvaluationRepository er;

    @Autowired
    private Tooltip ttip;

    public Boolean isDejaSoumis(Etudiant e, Integer ev){
        return !rer.findAllByNoEtudiantAndIdEvaluation(e, er.findById(ev).get()).isEmpty();
    }


}
