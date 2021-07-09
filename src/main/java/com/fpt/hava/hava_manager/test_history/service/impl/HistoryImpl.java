package com.fpt.hava.hava_manager.test_history.service.impl;

import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import com.fpt.hava.hava_manager.test_history.repository.HistoryRepository;
import com.fpt.hava.hava_manager.test_history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryImpl implements HistoryService {

  private final HistoryRepository historyRepository;

  @Override
  public HistoryEntity getHistoryById(Integer id) {
    HistoryEntity historyEntity = historyRepository.findAllById(id);
    return historyEntity;
  }

  @Override
  public Integer totalQuesByCat(Integer idExam, Integer idCat) {
    return historyRepository.totalQuesByCat(idExam, idCat);
  }

  @Override
  public HistoryEntity getLastRecord() {
    return historyRepository.findTopByOrderByIdDesc();
  }
}
