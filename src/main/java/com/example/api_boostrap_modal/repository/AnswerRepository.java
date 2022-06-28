package com.example.api_boostrap_modal.repository;

import com.example.api_boostrap_modal.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query(value = "select * from answer where question_id = :idQ", nativeQuery = true)
    Iterable<Answer> findAllByQuestionId(@Param("idQ") Long idQ);
}