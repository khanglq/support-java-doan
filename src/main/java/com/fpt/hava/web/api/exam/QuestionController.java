package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import com.fpt.hava.web.api.hava_manager.question.ApiUtil;
import com.fpt.hava.web.api.hava_manager.question.QuestionsApi;
import com.fpt.hava.web.api.hava_manager.question.dto.QuestionDTO;
import io.swagger.annotations.ApiParam;
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
  private final ModelMapper modelMapper;

  @Override
  public ResponseEntity<QuestionDTO> getQuestions(@ApiParam(value = "id of question",required=true) @PathVariable("id") Integer id) {
    QuestionDTO questionDTO = new QuestionDTO();
    QuestionsEntity questionsEntity = questionService.getQuestionById(id);

    modelMapper.map(questionsEntity, questionDTO);

    return ResponseEntity.ok(questionDTO);

  }
}
