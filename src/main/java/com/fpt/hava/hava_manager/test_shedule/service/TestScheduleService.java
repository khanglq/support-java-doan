package com.fpt.hava.hava_manager.test_shedule.service;

import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.hava_manager.test_shedule.domain.TestSchedulesEntity;

public interface TestScheduleService {
  TestSchedulesEntity findLastSchedule();
}
