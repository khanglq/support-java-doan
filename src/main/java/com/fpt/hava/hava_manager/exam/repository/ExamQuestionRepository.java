package com.fpt.hava.hava_manager.exam.repository;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestionsEntity, Integer> {
  List<ExamQuestionsEntity> findByExamId(Integer id);
}
