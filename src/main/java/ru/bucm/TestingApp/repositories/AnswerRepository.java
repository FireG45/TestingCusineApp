package ru.bucm.TestingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bucm.TestingApp.models.Answer;
import ru.bucm.TestingApp.models.Question;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    public List<Answer> findAllByQuestion(Question question);
}
