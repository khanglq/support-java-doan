package com.fpt.hava.hava_manager.test_history.service.impl;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.repository.ExamQuestionRepository;
import com.fpt.hava.hava_manager.exam.repository.ExamRepository;
import com.fpt.hava.hava_manager.exam.repository.QuestionRepository;
import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.hava_manager.test_history.repository.HistoryRepository;
import com.fpt.hava.hava_manager.test_history.repository.TestHistoryRepository;
import com.fpt.hava.hava_manager.test_history.service.TestHistoryService;
import com.fpt.hava.web.api.hava_manager.test_history.dto.SubList;
import com.fpt.hava.web.api.hava_manager.test_history.dto.TestHistoryCreateRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestHistoryImpl implements TestHistoryService {

  private final TestHistoryRepository testHistoryRepository;
  private final HistoryRepository historyRepository;
  private final QuestionRepository questionRepository;
  private final ExamQuestionRepository examQuestionRepository;
  private final ExamRepository examRepository;

  @Override
  public void CreateTestHistory(TestHistoryCreateRequest testHistoryCreateRequest) {
    List<ExamQuestionsEntity> listIdQuestion = examQuestionRepository.findByExamId(testHistoryCreateRequest.getIdExam());
    Optional<ExamsEntity> examsEntity = examRepository.findById(testHistoryCreateRequest.getIdExam());

    List<Integer> lstAnswerTrue = new ArrayList<>();
    for (ExamQuestionsEntity item : listIdQuestion){
      Optional<QuestionsEntity> answerTrue = questionRepository.findById(item.getId());

      lstAnswerTrue.add(answerTrue.get().getAnswerTrue());
    }

    int countTrueAnswer = 0;

    for (int i = 0; i < lstAnswerTrue.size(); i++){
      int selectedAnswer = testHistoryCreateRequest.getListAnswer().get(i).getAnswer();
      if(lstAnswerTrue.get(i) == selectedAnswer){
        countTrueAnswer++;
      }
    }

    HistoryEntity historyEntity = new HistoryEntity();
    historyEntity.setUserId(0);
    historyEntity.setExamId(testHistoryCreateRequest.getIdExam());
    historyEntity.setExamCategoryId(examsEntity.get().getCategoryId());
    historyEntity.setType(testHistoryCreateRequest.getType());
    historyEntity.setTrue_answer(countTrueAnswer);
    historyEntity.setTestSchedulesId(testHistoryCreateRequest.getTestSchedulesId());
    historyEntity.setTotalQuestion(lstAnswerTrue.size());

    historyRepository.save(historyEntity);

    for (int i = 0; i < lstAnswerTrue.size(); i++){
      SubList subList = testHistoryCreateRequest.getListAnswer().get(i);
      int selectedAnswer = subList.getAnswer();

      if(selectedAnswer != 0){
        TestHistoryEntity testHistoryEntity = new TestHistoryEntity();
        testHistoryEntity.setHistoryId(historyEntity.getId());
        testHistoryEntity.setQuestionId(subList.getIdQuestion());
        testHistoryEntity.setAnswer(subList.getAnswer());

        testHistoryRepository.save(testHistoryEntity);
      }
    }
  }
}
