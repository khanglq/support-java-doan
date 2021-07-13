package com.fpt.hava.web.api.test_history;

import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.service.QuestionService;
import com.fpt.hava.hava_manager.f_q_as.service.FQASService;
import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.hava_manager.test_history.service.HistoryService;
import com.fpt.hava.hava_manager.test_history.service.TestHistoryService;
import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.web.api.hava_manager.test_history.ApiUtil;
import com.fpt.hava.web.api.hava_manager.test_history.TestHistoryApi;
import com.fpt.hava.web.api.hava_manager.test_history.dto.Answers;
import com.fpt.hava.web.api.hava_manager.test_history.dto.TestHistoryCreateRequest;
import com.fpt.hava.web.api.hava_manager.test_history.dto.TestResultDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TestHistoryController implements TestHistoryApi {

  private final TestHistoryService testHistoryService;
  private final QuestionService questionService;
  private final HistoryService historyService;
  private final CategoryService categoryService;

//  @Override
//  public ResponseEntity<Void> createTestHistory(@ApiParam(value = "" ,required=true )  @Valid @RequestBody TestHistoryCreateRequest testHistoryCreateRequest) {
//    testHistoryService.createTestHistory(testHistoryCreateRequest);
//    return ResponseEntity.ok().build();
//  }

  @Override
  public ResponseEntity<Integer> createTestHistory(@ApiParam(value = "" ,required=true )  @Valid @RequestBody TestHistoryCreateRequest testHistoryCreateRequest) {
    testHistoryService.createTestHistory(testHistoryCreateRequest);
    HistoryEntity historyEntity = historyService.getLastRecord();

    return ResponseEntity.ok(historyEntity.getId());
  }

  @Override
  public ResponseEntity<List<TestResultDTO>> getTestResult(@ApiParam(value = "id of history",required=true) @PathVariable("id") String id) {
    HistoryEntity testHistory = historyService.getHistoryById(Integer.valueOf(id));
    List<QuestionsEntity> questionsLst = questionService.getAllQuestionByIdExam(testHistory.getExamId());

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

    for (int i = 0; i < mainLst.size(); i++){
      TestResultDTO testResultDTO = new TestResultDTO();

//      String title_category = categoryService.getCatById(mainLst.get(i)).getTitle();
      CategoryEntity categoryEntity = categoryService.getCatById(mainLst.get(i));

      Integer id_theory = -1;
      if (!categoryEntity.getDescription().equalsIgnoreCase("")){
        int temp = categoryEntity.getDescription().indexOf('#');
        String tempString = categoryEntity.getDescription().substring(temp+1);

        if(tempString.equalsIgnoreCase("")){
          id_theory = Integer.valueOf(testResultDTOS.get(i-1).getIdTheory());
        } else {
          id_theory = Integer.valueOf(tempString);
        }
      }

      testResultDTO.setTitle(categoryEntity.getTitle());
      testResultDTO.setIdTheory(id_theory);

      Integer totalQuesOfCat = historyService.totalQuesByCat(testHistory.getExamId(), mainLst.get(i));

      testResultDTO.setTotalQuestionOfCat(totalQuesOfCat);

      List<Answers> answersList = new ArrayList<>();
      int selectedQuesTrue = 0;
      for (int j = 0; j < questionsLst.size(); j++){
        if (questionsLst.get(j).getCategoryId() == mainLst.get(i)){
          Answers answers = new Answers();
          Optional<TestHistoryEntity> testHistoryEntity = testHistoryService.getTestHistoryByHisIdAndQuesId(Integer.valueOf(id), questionsLst.get(i).getId());
          answers.setQuestionNumber(j+1);
          if(testHistoryEntity.isPresent()){
            if (testHistoryEntity.get().getAnswer() == questionsLst.get(j).getAnswerTrue()){
              answers.setIsRight(1);
              selectedQuesTrue++;
            } else {
              answers.setIsRight(0);
            }
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
