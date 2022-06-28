package com.example.api_boostrap_modal.repository;

import com.example.api_boostrap_modal.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "select * from question order by id desc limit 1", nativeQuery = true)
    Optional<Question> findLastQuestion();
}