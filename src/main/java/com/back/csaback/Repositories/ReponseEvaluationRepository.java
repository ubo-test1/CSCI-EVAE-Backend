package com.back.csaback.Repositories;

import com.back.csaback.Models.Etudiant;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.Models.ReponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReponseEvaluationRepository extends JpaRepository<ReponseEvaluation, Integer> {
    public List<ReponseEvaluation> findAllByNoEtudiant(Etudiant e);
    List<ReponseEvaluation> findAllByNoEtudiantAndIdEvaluation(Etudiant noEtudiant, Evaluation idEvaluation);

    public List<ReponseEvaluation> findAllByIdEvaluation(Evaluation e);
}
