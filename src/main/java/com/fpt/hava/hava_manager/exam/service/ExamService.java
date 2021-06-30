package com.fpt.hava.hava_manager.exam.service;

import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import java.util.List;

public interface ExamService {
  List<ExamsEntity> getExamsByIdCategory(Integer id);
}
