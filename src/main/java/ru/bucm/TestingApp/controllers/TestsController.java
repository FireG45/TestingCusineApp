package ru.bucm.TestingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bucm.TestingApp.dto.TestDTO;
import ru.bucm.TestingApp.models.Answer;
import ru.bucm.TestingApp.models.Question;
import ru.bucm.TestingApp.models.Test;
import ru.bucm.TestingApp.models.User;
import ru.bucm.TestingApp.responses.TestUploadResponse;
import ru.bucm.TestingApp.services.AnswersService;
import ru.bucm.TestingApp.services.QuestionsService;
import ru.bucm.TestingApp.services.TestsService;
import ru.bucm.TestingApp.services.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/")
public class TestsController {

    final private TestsService testsService;
    final private UsersService usersService;
    final private QuestionsService questionsService;
    final private AnswersService answersService;
    @Autowired
    public TestsController(TestsService testsService, UsersService usersService, QuestionsService questionsService, AnswersService answersService) {
        this.testsService = testsService;
        this.usersService = usersService;
        this.questionsService = questionsService;
        this.answersService = answersService;
    }

    @GetMapping()
    public List<TestDTO> index() {
        List<Test> tests = testsService.findAll();
        List<TestDTO> testDTOS = new ArrayList<>();
        tests.forEach(t -> testDTOS.add(new TestDTO(t.getName(), t.getDescription(), t.getImage_link())));
        return testDTOS;
    }

    @PostMapping("/upload")
    public TestUploadResponse handleTestUpload(@RequestParam("name") String name,
                                               @RequestParam("description") String description,
                                               @RequestParam("imgUrl") String imgUrl,
                                               @RequestParam("authorId") int authorId,
                                               @RequestParam("questions") String[] questions,
                                               @RequestParam("questionsImgs") String[] questionsImgs,
                                               @RequestParam("answers") String[][] answers) {
        Optional<User> user = usersService.getById(authorId);
        if (user.isEmpty()) return new TestUploadResponse(-1, HttpStatus.BAD_REQUEST);
        Test test = new Test(name, description, imgUrl, user.get());
        List<Question> questionModels = new ArrayList<>();
        List<Answer> answerModels = new ArrayList<>();
        for (int i = 0; i < Math.min(questions.length, questionsImgs.length); i++) {
            Question question = new Question(questions[i], questionsImgs[i], test);
            questionModels.add(question);
            for (String ans : answers[i]) answerModels.add(new Answer(ans, question));
        }
        questionsService.saveAll(questionModels);
        answersService.saveAll(answerModels);
        int id = Math.toIntExact(testsService.save(test).getId());
        return new TestUploadResponse(id, HttpStatus.OK);
    }
}
