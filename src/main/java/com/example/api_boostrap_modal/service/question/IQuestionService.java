package com.example.api_boostrap_modal.service.question;

import com.example.api_boostrap_modal.model.Question;
import com.example.api_boostrap_modal.service.GeneralService;

import java.util.Optional;

public interface IQuestionService extends GeneralService<Question> {
    Optional<Question> findLastQuestion();
}
