package com.fpt.hava.hava_manager.exam.service.impl;

import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import com.fpt.hava.hava_manager.exam.repository.ExamRepository;
import com.fpt.hava.hava_manager.exam.service.ExamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamImpl implements ExamService {

  private final ExamRepository examRepository;

  @Override
  public List<ExamsEntity> getExamsByIdCategory(Integer id) {
    List<ExamsEntity> examsEntities = examRepository.findAllByCategoryId(id);
    return examsEntities;
  }
}
