package com.example.api_boostrap_modal.controller;

import com.example.api_boostrap_modal.model.Answer;
import com.example.api_boostrap_modal.model.Question;
import com.example.api_boostrap_modal.service.answer.AnswerService;
import com.example.api_boostrap_modal.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping
public class HomeController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    public Question randomQuestion() {
        ArrayList<Question> questions = (ArrayList<Question>) questionService.findAll();
        int index = (int) Math.floor(Math.random() * questions.size());
        return questions.get(index);
    }

    @GetMapping
    public ResponseEntity<Object[]> getQuestionAndAnswer() {
        Question question = randomQuestion();
        Iterable<Answer> answers = answerService.findAllByQuestionId(question.getId());
        Object[] abc = {question, answers};
        return new ResponseEntity<>(abc, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Optional<Question>> createQuestion(@Valid @RequestBody Question question) {
        questionService.save(question);
        return new ResponseEntity<>(questionService.findLastQuestion(), HttpStatus.CREATED);
    }

    @PostMapping("/answers")
    public ResponseEntity<Question> createAnswer(@RequestBody    Answer answer) {
        answerService.save(answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}