package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.service.ExamQuestionService;
import com.fpt.hava.web.api.hava_manager.exam.dto.ExamDTO;
import com.fpt.hava.web.api.hava_manager.exam_question.ApiUtil;
import com.fpt.hava.web.api.hava_manager.exam_question.ListQuestionApi;
import com.fpt.hava.web.api.hava_manager.exam_question.dto.QuestionIdDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
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
public class ExamQuestionController implements ListQuestionApi {

  private final ExamQuestionService examQuestionService;
  private final ModelMapper modelMapper;

  @Override
  public ResponseEntity<List<QuestionIdDTO>> getListIdQuestion(@ApiParam(value = "id of exam",required=true) @PathVariable("id") Integer id) {
    List<QuestionIdDTO> questionIdDTOS = new ArrayList<>();
    List<ExamQuestionsEntity> examQuestionsEntities = examQuestionService.getIdQuestionByExamId(id);

    for(ExamQuestionsEntity item : examQuestionsEntities){
      QuestionIdDTO questionIdDTO = new QuestionIdDTO();

      modelMapper.map(item, questionIdDTO);
      questionIdDTOS.add(questionIdDTO);
    }

    return ResponseEntity.ok(questionIdDTOS);

  }
}
