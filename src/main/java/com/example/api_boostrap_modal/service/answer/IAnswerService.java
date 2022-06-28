package com.example.api_boostrap_modal.service.answer;

import com.example.api_boostrap_modal.model.Answer;
import com.example.api_boostrap_modal.service.GeneralService;

public interface IAnswerService extends GeneralService<Answer> {
    Iterable<Answer> findAllByQuestionId(Long idQ);
}
