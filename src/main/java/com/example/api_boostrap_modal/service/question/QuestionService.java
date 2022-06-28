package com.example.api_boostrap_modal.service.question;

import com.example.api_boostrap_modal.model.Question;
import com.example.api_boostrap_modal.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class QuestionService implements IQuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Optional<Question> findLastQuestion() {
        return questionRepository.findLastQuestion();
    }
}
