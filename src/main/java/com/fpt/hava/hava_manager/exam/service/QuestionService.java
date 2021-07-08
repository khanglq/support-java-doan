package com.fpt.hava.hava_manager.exam.service;

import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import java.util.List;

public interface QuestionService {
  QuestionsEntity getQuestionById(Integer id);
  List<QuestionsEntity> getAllQuestionByIdExam(Integer id);
}
