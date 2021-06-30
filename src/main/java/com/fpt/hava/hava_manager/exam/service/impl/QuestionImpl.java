package com.fpt.hava.hava_manager.exam.service.impl;

import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.repository.QuestionRepository;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionImpl implements QuestionService {

  private final QuestionRepository questionRepository;

  @Override
  public QuestionsEntity getQuestionById(Integer id) {
    QuestionsEntity questionsEntity = questionRepository.getById(id);
    return questionsEntity;
  }
}
