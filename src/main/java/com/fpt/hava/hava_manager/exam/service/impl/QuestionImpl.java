package com.fpt.hava.hava_manager.exam.service.impl;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.repository.ExamQuestionRepository;
import com.fpt.hava.hava_manager.exam.repository.QuestionRepository;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final ExamQuestionRepository examQuestionRepository;

  @Override
  public QuestionsEntity getQuestionById(Integer id) {
    QuestionsEntity questionsEntity = questionRepository.getById(id);

    return questionsEntity;
  }

  @Override
  public List<QuestionsEntity> getAllQuestionByIdExam(Integer id) {
    List<ExamQuestionsEntity> examQuestionsEntities = examQuestionRepository.findByExamId(id);
    List<QuestionsEntity> questionsEntities = new ArrayList<>();

    for (ExamQuestionsEntity item : examQuestionsEntities){
      QuestionsEntity questionsEntity = questionRepository.getById(item.getQuestionId());
      if (questionsEntity.getAnswerTrue() != 0){
        questionsEntities.add(questionsEntity);
      }
    }

    return questionsEntities;
  }
}
