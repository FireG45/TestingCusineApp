package ru.bucm.TestingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bucm.TestingApp.models.Question;
import ru.bucm.TestingApp.models.Test;
import ru.bucm.TestingApp.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    final private QuestionRepository questionRepository;

    @Autowired
    public QuestionsService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> findById(int id) {
        return questionRepository.findById(id);
    }

    public List<Question> findAllByTest(Test test) {
        return questionRepository.findAllByTest(test);
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public void saveAll(List<Question> questions) {
        questionRepository.saveAll(questions);
    }
}
