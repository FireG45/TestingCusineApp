package ru.bucm.TestingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bucm.TestingApp.models.Question;
import ru.bucm.TestingApp.models.Test;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public List<Question> findAllByTest(Test test);
}
