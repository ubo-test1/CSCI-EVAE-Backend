package com.back.csaback.Services;


import com.back.csaback.DTO.QuestionAssociated;
import com.back.csaback.Exceptions.ErrorQuestionAlreadyExist;
import com.back.csaback.Exceptions.ErrorQuestionAssociated;
import com.back.csaback.Models.Question;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
/**
 * Service responsable de la gestion des questions standard.
 * Ce service offre des fonctionnalités telles que la sauvegarde, la récupération, la mise à jour et la suppression des questions standard.
 * Il fournit également des méthodes pour vérifier si une question est associée à une rubrique.
 * Les questions standard sont gérées en interagissant avec les référentiels de données {@link QuestionRepository} et {@link RubriqueQuestionRepository}.
 * Les exceptions {@link ErrorQuestionAssociated} et {@link EntityNotFoundException} sont utilisées pour signaler des erreurs spécifiques.
 *  @author Achraf EL KRISSI
 *  @version V2
 *  @since 20/02/2024
 */


@Service
public class QuestionStandardService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    RubriqueQuestionRepository rubriqueQuestionRepository;
    public Question save(Question newQuestion) {
         if (questionRepository.existsByIntitule(newQuestion.getIntitule())) throw new ErrorQuestionAlreadyExist("la question déja existe !!");
         else   return questionRepository.save(newQuestion);
    }
    public List<QuestionAssociated> getAll() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionAssociated> questionAssociateds= new ArrayList<>();
        for (Question question:questions){
            QuestionAssociated questionAssociated = new QuestionAssociated();
            if(isQuestionAssociated(question.getId())) questionAssociated.setAssociated(true);
            else questionAssociated.setAssociated(false);
            questionAssociated.setQuestion(question);
            questionAssociateds.add(questionAssociated);
        }
        return questionAssociateds;
    }
    public void delete(Integer id) {
        if (isQuestionAssociated(id)) {
            throw new ErrorQuestionAssociated("Cette question est déjà liée à une rubrique.");
        } else {
            Question questionToDelete = findById(id);
            if (questionToDelete != null) {
                questionRepository.delete(questionToDelete);
            } else {
                throw new IllegalArgumentException("La question avec l'ID " + id + " n'existe pas.");
            }
        }
    }
    public Question update(Question ques) {
        findById(ques.getId());
        if (isQuestionAssociated(ques.getId())) {
            throw new ErrorQuestionAssociated("Cette question est déjà liée à une rubrique.");
        } else {
            return questionRepository.save(ques);
        }
    }
    public Question findById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La question avec l'ID " + id + " n'existe pas."));
    }
    boolean isQuestionAssociated(Integer id){
        List<RubriqueQuestion>  rubriqueQuestions =rubriqueQuestionRepository.findAll();
    for (RubriqueQuestion rubriqueQuestion:rubriqueQuestions){
        if (Objects.equals(rubriqueQuestion.getIdQuestion().getId(), id)) return true;
    }
    return false;
    }


}
