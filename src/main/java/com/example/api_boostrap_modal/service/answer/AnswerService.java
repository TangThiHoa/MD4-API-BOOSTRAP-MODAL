package com.example.api_boostrap_modal.service.answer;

import com.example.api_boostrap_modal.model.Answer;
import com.example.api_boostrap_modal.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService implements IAnswerService{
    @Autowired
    AnswerRepository answerRepository;


    @Override
    public Iterable<Answer> findAll() {
        return null;
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Answer answer) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<Answer> findAllByQuestionId(Long idQ) {
        return null;
    }
}