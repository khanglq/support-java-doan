package com.fpt.hava.hava_manager.test_history.service;

import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import java.util.List;

public interface HistoryService {
  HistoryEntity getHistoryById(Integer id);
  Integer totalQuesByCat(Integer idExam, Integer idCat);
  HistoryEntity getLastRecord();
}
