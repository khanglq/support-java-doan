package com.fpt.hava.hava_manager.exam.repository;

import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<QuestionsEntity, Integer> {

  @Override
  Optional<QuestionsEntity> findById(Integer integer);
}
