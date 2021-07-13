package com.fpt.hava.hava_manager.theory.service;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import java.util.List;

public interface TheoryService {
  List<TheoryEntity> getAllByIdCat(Integer id);
  TheoryEntity findAllById(Integer id);
}
