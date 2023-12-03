package ru.bucm.TestingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bucm.TestingApp.dto.TestDTO;
import ru.bucm.TestingApp.models.Test;
import ru.bucm.TestingApp.services.TestsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {
    final private TestsService testsService;

    @Autowired
    public IndexController(TestsService testsService) {
        this.testsService = testsService;
    }

    @GetMapping()
    public List<TestDTO> index() {
        List<Test> tests = testsService.findAll();
        List<TestDTO> testDTOS = new ArrayList<>();
        tests.forEach(t -> testDTOS.add(new TestDTO(t.getName(), t.getDescription(), t.getImage_link())));
        return testDTOS;
    }
}
