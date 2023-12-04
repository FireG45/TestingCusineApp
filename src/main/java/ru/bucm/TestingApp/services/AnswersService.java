package ru.bucm.TestingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bucm.TestingApp.models.Answer;
import ru.bucm.TestingApp.models.Question;
import ru.bucm.TestingApp.repositories.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswersService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswersService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Optional<Answer> findById(int id) {
        return answerRepository.findById(id);
    }

    public List<Answer> findByQuestion(Question question) {
        return answerRepository.findAllByQuestion(question);
    }

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    public void saveAll(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }
}
