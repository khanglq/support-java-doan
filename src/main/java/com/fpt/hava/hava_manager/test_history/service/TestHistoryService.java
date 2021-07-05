package com.fpt.hava.hava_manager.test_history.service;

import com.fpt.hava.web.api.hava_manager.test_history.dto.TestHistoryCreateRequest;

public interface TestHistoryService {
  void CreateTestHistory(TestHistoryCreateRequest testHistoryCreateRequest);
}
