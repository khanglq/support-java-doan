package com.fpt.hava.hava_manager.test_history.service;

import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.web.api.hava_manager.test_history.dto.TestHistoryCreateRequest;
import java.util.Optional;

public interface TestHistoryService {
  void createTestHistory(TestHistoryCreateRequest testHistoryCreateRequest);
  TestHistoryEntity getHistoryById(Integer id);
  Optional<TestHistoryEntity> getTestHistoryByHisIdAndQuesId(Integer idHis, Integer idQues);
}
