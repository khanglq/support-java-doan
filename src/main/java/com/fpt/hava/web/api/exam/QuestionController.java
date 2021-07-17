package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.ExamQuestionsEntity;
import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.domain.QuestionsEntity;
import com.fpt.hava.hava_manager.exam.service.ExamQuestionService;
import com.fpt.hava.hava_manager.exam.service.ExamService;
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
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
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
  private final ExamService examService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<QuestionDTO>> getQuestions(@ApiParam(value = "id of exam",required=true) @PathVariable("id") String id) {
    List<QuestionDTO> questionDTOLst = new ArrayList<>();
    List<ExamQuestionsEntity> examQuestion = examQuestionService.getIdQuestionByExamId(Integer.valueOf(id));

    int index = 1;
    for (int i = 0; i < examQuestion.size(); i++){
      QuestionDTO questionDTO = new QuestionDTO();
      QuestionsEntity questionsEntity = questionService.getQuestionById(examQuestion.get(i).getQuestionId());
      questionsEntity.setQuestion(questionsEntity.getQuestion().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-top:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-top:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-top:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-bottom:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-bottom:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-bottom:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-left:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-left:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-left:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-right:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-right:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-right:solid", ""));

      questionsEntity.setAnswer(questionsEntity.getAnswer().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerA(questionsEntity.getAnswerA().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerB(questionsEntity.getAnswerB().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerC(questionsEntity.getAnswerC().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerD(questionsEntity.getAnswerD().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));

      String escapedHTML ;
      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getQuestion());
      questionsEntity.setQuestion(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswer());
      questionsEntity.setAnswer(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerA());
      questionsEntity.setAnswerA(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerB());
      questionsEntity.setAnswerB(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerC());
      questionsEntity.setAnswerC(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerD());
      questionsEntity.setAnswerD(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getHints());
      questionsEntity.setHints(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getComments());
      questionsEntity.setComments(escapedHTML);

      modelMapper.map(questionsEntity, questionDTO);

      Optional<LabelsEntity> label = labelService.getLabelById(questionDTO.getLabelId());

      if(label.isPresent()){
        questionDTO.setValueLable(label.get().getTitle());
      }

      if (questionDTO.getAnswerTrue() != 0){
        questionDTO.setIndex("Câu "+ index +":");
        index++;
      }

      questionDTOLst.add(questionDTO);
    }

    return ResponseEntity.ok(questionDTOLst);
  }

  public ResponseEntity<List<QuestionDTO>> getRandomQuestions(@ApiParam(value = "id of category",required=true) @PathVariable("id") String id) {
    List<ExamsEntity> examsEntities = examService.getExamsByIdCategory(Integer.valueOf(id));

    Random random = new Random();
    int randomdIndexExam = random.nextInt(examsEntities.size());

    int idExam = examsEntities.get(randomdIndexExam).getId();
    List<QuestionsEntity> questionDTOS = questionService.getAllQuestionByIdExam(idExam);
    List<QuestionDTO> questionDTOLst = new ArrayList<>();

    int index = 1;
    for (QuestionsEntity questionsEntity : questionDTOS){
      QuestionDTO questionDTO = new QuestionDTO();
      questionsEntity.setQuestion(questionsEntity.getQuestion().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-top:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-top:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-top:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-bottom:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-bottom:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-bottom:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-left:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-left:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-left:solid", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-right:none;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-right:solid;", ""));
      questionsEntity.setQuestion(questionsEntity.getQuestion().replaceAll("border-right:solid", ""));

      questionsEntity.setAnswer(questionsEntity.getAnswer().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerA(questionsEntity.getAnswerA().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerB(questionsEntity.getAnswerB().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerC(questionsEntity.getAnswerC().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      questionsEntity.setAnswerD(questionsEntity.getAnswerD().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));

      String escapedHTML ;
      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getQuestion());
      questionsEntity.setQuestion(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswer());
      questionsEntity.setAnswer(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerA());
      questionsEntity.setAnswerA(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerB());
      questionsEntity.setAnswerB(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerC());
      questionsEntity.setAnswerC(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getAnswerD());
      questionsEntity.setAnswerD(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getHints());
      questionsEntity.setHints(escapedHTML);

      escapedHTML = StringEscapeUtils.escapeHtml4(questionsEntity.getComments());
      questionsEntity.setComments(escapedHTML);

      modelMapper.map(questionsEntity, questionDTO);

      Optional<LabelsEntity> label = labelService.getLabelById(questionDTO.getLabelId());
      if(label.isPresent()){
        questionDTO.setValueLable(label.get().getTitle());
      }

      if (questionDTO.getAnswerTrue() != 0){
        questionDTO.setIndex("Câu "+ index +":");
        index++;
      }

      questionDTO.setIdExam(idExam);
      questionDTOLst.add(questionDTO);
    }

    return ResponseEntity.ok(questionDTOLst);
  }
}
