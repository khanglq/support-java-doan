package com.fpt.hava.web.api.test_schedule;

import com.fpt.hava.hava_manager.test_shedule.domain.TestSchedulesEntity;
import com.fpt.hava.hava_manager.test_shedule.service.TestScheduleService;
import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.web.api.hava_manager.test_schedule.ApiUtil;
import com.fpt.hava.web.api.hava_manager.test_schedule.TestScheduleApi;
import com.fpt.hava.web.api.hava_manager.test_schedule.dto.TestScheduleDTO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TestScheduleController implements TestScheduleApi {

  private final TestScheduleService testScheduleService;
  private final CategoryService categoryService;
  private final ModelMapper modelMapper;

  public ResponseEntity<TestScheduleDTO> getSchedule() {
    TestSchedulesEntity testSchedulesEntity = testScheduleService.findLastSchedule();

    TestScheduleDTO testScheduleDTO = new TestScheduleDTO();
    modelMapper.map(testSchedulesEntity, testScheduleDTO);

    CategoryEntity categoryEntity = categoryService.getCatById(testSchedulesEntity.getCategoryId());

    testScheduleDTO.setSubjectName(categoryEntity.getTitle());

    return ResponseEntity.ok(testScheduleDTO);

  }
}
