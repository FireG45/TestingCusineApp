package ru.bucm.TestingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bucm.TestingApp.models.Test;
import ru.bucm.TestingApp.repositories.TestsRepository;

import java.util.List;

@Service
public class TestsService {

    final private TestsRepository testsRepository;

    @Autowired
    public TestsService(TestsRepository testsRepository) {
        this.testsRepository = testsRepository;
    }

    public List<Test> findAll() {
        return testsRepository.findAll();
    }

    public Test save(Test test) {
        return testsRepository.save(test);
    }

}
