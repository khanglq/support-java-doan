package com.fpt.hava.web.api.exam;

import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.service.ExamService;
import com.fpt.hava.web.api.hava_manager.exam.ExamsApi;
import com.fpt.hava.web.api.hava_manager.exam.dto.ExamDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExamController implements ExamsApi {

  private final ExamService examService;
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
}
