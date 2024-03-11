package com.back.csaback.Services;


import com.back.csaback.Exceptions.OverlappingRecordsException;
import com.back.csaback.Models.Etudiant;
import com.back.csaback.Models.ReponseEvaluation;
import com.back.csaback.Models.ReponseQuestion;
import com.back.csaback.Repositories.EtudiantRepository;
import com.back.csaback.Repositories.EvaluationRepository;
import com.back.csaback.Repositories.ReponseEvaluationRepository;
import com.back.csaback.Repositories.ReponseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private ReponseEvaluationRepository rer;

    @Autowired
    private EvaluationRepository er;

    @Autowired
    private EtudiantRepository etr;

    @Autowired
    private Tooltip ttip;

    @Autowired
    private ReponseQuestionRepository rqr;

    public Boolean isDejaSoumis(Etudiant e, Integer ev){
        return !rer.findAllByNoEtudiantAndIdEvaluation(e, er.findByCustomQuery(ev).get()).isEmpty();
    }

    public ReponseEvaluation getReponseEvaluation(Etudiant e, Integer idEva){
        List<ReponseEvaluation> tmp = rer.findAllByNoEtudiantAndIdEvaluation(e,er.findByCustomQuery(idEva).get());
        if(tmp.size() > 1){
            throw new OverlappingRecordsException("Overlapping records check database");
        }
        return tmp.get(0);
    }
    public List<ReponseQuestion> getReponseQuestions(ReponseEvaluation rev){
        return rqr.findAllByIdReponseEvaluation(rev);
    }
}
