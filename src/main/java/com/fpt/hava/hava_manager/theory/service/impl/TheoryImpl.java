package com.fpt.hava.hava_manager.theory.service.impl;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.hava_manager.theory.repository.TheoryRepository;
import com.fpt.hava.hava_manager.theory.service.TheoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheoryImpl implements TheoryService {

  private final TheoryRepository theoryRepository;

  @Override
  public TheoryEntity getAllById(Integer id) {
    TheoryEntity theoryEntity = theoryRepository.findAllById(id);
    return theoryEntity;
  }
}
