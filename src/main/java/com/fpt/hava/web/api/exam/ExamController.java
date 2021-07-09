package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.service.ExamService;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.web.api.hava_manager.exam.ApiUtil;
import com.fpt.hava.web.api.hava_manager.exam.ExamApi;
import com.fpt.hava.web.api.hava_manager.exam.dto.Answers;
import com.fpt.hava.web.api.hava_manager.exam.dto.ExamDTO;
import com.fpt.hava.web.api.hava_manager.exam.dto.ExamRequest;
import com.fpt.hava.web.api.hava_manager.exam.dto.TestResultDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExamController implements ExamApi {

  private final ExamService examService;
  private final QuestionService questionService;
  private final CategoryService categoryService;
  private final ModelMapper modelMapper;

  @Override
  public ResponseEntity<List<ExamDTO>> getExams(@ApiParam(value = "id of category",required=true) @PathVariable("id") String id) {
    List<ExamDTO> examDTOS = new ArrayList<>();
    List<ExamsEntity> exams = examService.getExamsByIdCategory(Integer.valueOf(id));

    for(ExamsEntity item : exams){
      ExamDTO examDTO = new ExamDTO();

      if(item.getIsTheory() == 0 && item.getIsRandom() == 0 && item.getIsPrivate() == 0 && item.getIsDone() == 1){
        modelMapper.map(item, examDTO);
        examDTOS.add(examDTO);
      }
    }

    return ResponseEntity.ok(examDTOS);
  }

  @Override
  public ResponseEntity<TestResultDTO> getMark(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ExamRequest examRequest) {
    List<QuestionsEntity> questionsEntities = questionService.getAllQuestionByIdExam(examRequest.getIdExam());

    List<Integer> lstAnswerTrue = new ArrayList<>();
    for(QuestionsEntity item : questionsEntities){
      lstAnswerTrue.add(item.getAnswerTrue());
    }

    List<Answers> answerLst = new ArrayList<>();
    int totalQuestionTrue = 0;
    for(int i=0; i < lstAnswerTrue.size(); i++){
      Answers answer = new Answers();
      answer.setQuestionNumber(i+1);
      answer.setIsRight(0);
      if(examRequest.getListAnswer().get(i).getAnswer() == lstAnswerTrue.get(i)){
        totalQuestionTrue++;
        answer.setIsRight(1);
      }
      answerLst.add(answer);
    }

    Optional<CategoryEntity> categoryEntity = categoryService.findById(questionsEntities.get(0).getCategoryId());

    TestResultDTO testResultDTO = new TestResultDTO();
    testResultDTO.setTitle(categoryEntity.get().getTitle());
    testResultDTO.setTotalQuestion(lstAnswerTrue.size());
    testResultDTO.setTotalQuestionTrue(totalQuestionTrue);
    testResultDTO.setListAnswer(answerLst);

    return ResponseEntity.ok(testResultDTO);

  }
}
