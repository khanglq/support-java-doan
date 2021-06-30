package com.fpt.hava.hava_manager.exam.service;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import java.util.List;

public interface ExamQuestionService {
  List<ExamQuestionsEntity> getIdQuestionByExamId(Integer id);
}
