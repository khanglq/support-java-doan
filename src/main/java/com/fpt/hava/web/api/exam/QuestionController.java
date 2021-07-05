package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.service.ExamQuestionService;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import com.fpt.hava.hava_manager.label.domain.LabelsEntity;
import com.fpt.hava.hava_manager.label.service.LabelService;
import com.fpt.hava.web.api.hava_manager.question.ApiUtil;
import com.fpt.hava.web.api.hava_manager.question.QuestionsApi;
import com.fpt.hava.web.api.hava_manager.question.dto.QuestionDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController implements QuestionsApi {

  private final QuestionService questionService;
  private final ExamQuestionService examQuestionService;
  private final LabelService labelService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<QuestionDTO>> getQuestions(@ApiParam(value = "id of exam",required=true) @PathVariable("id") String id) {
    List<QuestionDTO> questionDTOLst = new ArrayList<>();
    List<ExamQuestionsEntity> examQuestion = examQuestionService.getIdQuestionByExamId(Integer.valueOf(id));

    for (int i = 0; i < examQuestion.size(); i++){
      QuestionDTO questionDTO = new QuestionDTO();
      QuestionsEntity questionsEntity = questionService.getQuestionById(examQuestion.get(i).getQuestionId());
      questionsEntity.setQuestion(questionsEntity.getQuestion().replace("/uploads/images/","hava.edu.vn/uploads/images/"));
      modelMapper.map(questionsEntity, questionDTO);

      Optional<LabelsEntity> label = labelService.getLabelById(questionDTO.getLabelId());
      if(label.isPresent()){
        questionDTO.setValueLable(label.get().getTitle());
      }

      questionDTOLst.add(questionDTO);
    }

    return ResponseEntity.ok(questionDTOLst);
  }
}
