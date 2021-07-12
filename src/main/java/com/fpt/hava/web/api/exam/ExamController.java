package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.service.ExamService;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.hava_manager.test_history.service.HistoryService;
import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.web.api.hava_manager.exam.ApiUtil;
import com.fpt.hava.web.api.hava_manager.exam.ExamApi;
import com.fpt.hava.web.api.hava_manager.exam.dto.Answers;
import com.fpt.hava.web.api.hava_manager.exam.dto.ExamDTO;
import com.fpt.hava.web.api.hava_manager.exam.dto.ExamRequest;
import com.fpt.hava.web.api.hava_manager.exam.dto.SubList;
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
  private final HistoryService historyService;
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
  public ResponseEntity<List<TestResultDTO>> getMark(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ExamRequest examRequest) {
    List<QuestionsEntity> questionsLst = questionService.getAllQuestionByIdExam(examRequest.getIdExam());
    List<SubList> subLists = examRequest.getListAnswer();

    // get list id category in list question
    List<Integer> catIdLst = new ArrayList<>();
    for (QuestionsEntity item : questionsLst){
      int ids = item.getCategoryId();
      if(item.getAnswerTrue() != 0){
        catIdLst.add(ids);
      }
    }

    // get distinct id category
    List<Integer> mainLst = new ArrayList<>();
    mainLst.add(catIdLst.get(0));
    for (int i = 0; i < catIdLst.size(); i++){
      boolean check = true;
      for (int j = 0; j < mainLst.size(); j++){
        if(catIdLst.get(i) == mainLst.get(j)){
          check = false;
        }
      }
      if (check == true) mainLst.add(catIdLst.get(i));
    }
    //

    List<TestResultDTO> testResultDTOS = new ArrayList<>();
    int totalQuestionTrue = 0;

    for (Integer item : mainLst){
      TestResultDTO testResultDTO = new TestResultDTO();

      String title_category = categoryService.getCatById(item).getTitle();
      CategoryEntity categoryEntity = categoryService.findByTitle(title_category);
      int temp = categoryEntity.getDescription().indexOf('#');
      Integer id_theory = Integer.valueOf(categoryEntity.getDescription().substring(temp+1));
      testResultDTO.setTitle(title_category);
      testResultDTO.setIdTheory(id_theory);

      Integer totalQuesOfCat = historyService.totalQuesByCat(examRequest.getIdExam(), item);

      testResultDTO.setTotalQuestionOfCat(totalQuesOfCat);

      List<Answers> answersList = new ArrayList<>();
      int selectedQuesTrue = 0;
      for (int i = 0; i < questionsLst.size(); i++){
        if (questionsLst.get(i).getId() == subLists.get(i).getIdQuestion() && questionsLst.get(i).getCategoryId() == item){
          Answers answers = new Answers();
          answers.setQuestionNumber(i+1);
          if (subLists.get(i).getAnswer() == questionsLst.get(i).getAnswerTrue()){
            answers.setIsRight(1);
            selectedQuesTrue++;
          } else {
            answers.setIsRight(0);
          }
          answersList.add(answers);
        }
      }

      totalQuestionTrue += selectedQuesTrue;

      testResultDTO.setSelectedQuestionTrue(selectedQuesTrue);
      testResultDTO.setListAnswer(answersList);
      testResultDTOS.add(testResultDTO);
    }
    testResultDTOS.get(0).setTotalQuestion(questionsLst.size());
    testResultDTOS.get(0).setTotalQuestionTrue(totalQuestionTrue);

    return ResponseEntity.ok(testResultDTOS);

  }
}
