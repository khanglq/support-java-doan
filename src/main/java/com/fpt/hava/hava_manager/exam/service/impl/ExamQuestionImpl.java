package com.fpt.hava.hava_manager.exam.service.impl;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.repository.ExamQuestionRepository;
import com.fpt.hava.hava_manager.exam.service.ExamQuestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamQuestionImpl implements ExamQuestionService {

  private final ExamQuestionRepository examQuestionRepository;

  @Override
  public List<ExamQuestionsEntity> getIdQuestionByExamId(Integer id) {
    List<ExamQuestionsEntity> examQuestionsEntities = examQuestionRepository.findByExamId(id);
    return examQuestionsEntities;
  }
}
