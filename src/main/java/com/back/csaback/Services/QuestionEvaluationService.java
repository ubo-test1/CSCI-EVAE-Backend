package com.back.csaback.Services;


import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Models.QuestionEvaluation;
import com.back.csaback.Models.RubriqueEvaluation;
import com.back.csaback.Repositories.QuestionEvaluationRepository;
import com.back.csaback.Repositories.RubriqueEvaluationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Service responsable de la gestion des questions des evaluations.
 * Ce service offre des fonctionnalités telles que la sauvegarde, la récupération, la mise à jour et la suppression des questions des evaluations.
 * Les questions standard sont gérées en interagissant avec les référentiels de données {@link QuestionEvaluationRepository} et {@link RubriqueEvaluationRepository}.
 * Les exceptions {@link ErrorQuestionAlreadyExist} et {@link EntityNotFoundException} sont utilisées pour signaler des erreurs spécifiques.
 *
 *  @author Achraf EL KRISSI
 *  @version V1
 *  @since 04/02/2024
 */
@Service
public class QuestionEvaluationService {

    @Autowired
    QuestionEvaluationRepository questionEvaluationRepository;
    @Autowired
    RubriqueEvaluationRepository rubriqueEvaluationRepository;
    public QuestionEvaluation save(QuestionEvaluation newQuestionEvaluation) {
        if (questionEvaluationRepository.existsByIdQuestionAndIdRubriqueEvaluation(newQuestionEvaluation.getIdQuestion(),newQuestionEvaluation.getIdRubriqueEvaluation())) throw new ErrorQuestionAlreadyExist("la question déja existe !!");
        else   return questionEvaluationRepository.save(newQuestionEvaluation);
    }
    public void delete(Integer id) {
        QuestionEvaluation questionToDelete =findById(id);
        if (questionToDelete != null) {
            questionEvaluationRepository.delete(questionToDelete);
        } else {
            throw new IllegalArgumentException("La question avec l'ID " + id + " n'existe pas.");
        }
    }
    public QuestionEvaluation findById(Integer id) {
        return questionEvaluationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La question avec l'ID " + id + " n'existe pas."));
    }

   public List<QuestionEvaluation>  getAll(){
        return questionEvaluationRepository.findAll();
   }

    public List<QuestionEvaluation> findByIdRubrique(Integer id) {
        RubriqueEvaluation rubriqueEvaluation = rubriqueEvaluationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La rubrique avec l'ID " + id + " n'existe pas."));
        return questionEvaluationRepository.findAllByidRubriqueEvaluation(rubriqueEvaluation);
    }
    public QuestionEvaluation update(QuestionEvaluation questionEvaluation) {
        //findById(questionEvaluation.getId());
        return questionEvaluationRepository.save(questionEvaluation);
    }
}
