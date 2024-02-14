package com.back.csaback.Services;

import com.back.csaback.Models.Question;
import com.back.csaback.Models.Rubrique;
import com.back.csaback.Models.RubriqueQuestion;
import com.back.csaback.Models.RubriqueQuestionId;
import com.back.csaback.Repositories.QuestionRepository;
import com.back.csaback.Repositories.RubriqueQuestionRepository;
import com.back.csaback.Repositories.RubriqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RubriqueQuestionService {
    @Autowired
    private RubriqueQuestionRepository rubriqueQuestionRepository;
    @Autowired
    private RubriqueRepository rubriqueRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public RubriqueQuestion saveRubriqueQuestion(RubriqueQuestion rubriqueQuestion) {
        return rubriqueQuestionRepository.save(rubriqueQuestion);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAllByIdRubrique(Rubrique id){
         rubriqueQuestionRepository.deleteAllByIdRubrique(id);
    }
    public void deleteById(RubriqueQuestionId rubriqueQuestionId){
        rubriqueQuestionRepository.deleteById(rubriqueQuestionId);
    }

    public Optional<RubriqueQuestion> getRubriqueQuestion(RubriqueQuestionId id) {
        return rubriqueQuestionRepository.findById(id);
    }

   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateByOrdre(RubriqueQuestion rubriqueQuestionId) {
        rubriqueQuestionRepository.save(rubriqueQuestionId);
    }
    public boolean existsByRubriqueAndQuestion(Rubrique rubrique, Question question) {
        return rubriqueQuestionRepository.existsByIdRubriqueAndIdQuestion(rubrique,question);
    }

    public List<RubriqueQuestion> getAllRubriqueQuestionsByRubriqueId(Long rubriqueId) {
        Rubrique rubrique = rubriqueRepository.findById(rubriqueId).orElse(null);
        List<RubriqueQuestion> rubriqueQuestions = rubriqueQuestionRepository.findAllByIdRubrique(rubrique);
        return rubriqueQuestions;
    }

}
