package com.fpt.hava.hava_manager.test_shedule.service.impl;

import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.hava_manager.test_shedule.domain.TestSchedulesEntity;
import com.fpt.hava.hava_manager.test_shedule.repository.TestScheduleRepository;
import com.fpt.hava.hava_manager.test_shedule.service.TestScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestScheduleImpl implements TestScheduleService {

  private final TestScheduleRepository testScheduleRepository;

  @Override
  public TestSchedulesEntity findLastSchedule() {
    return testScheduleRepository.findLastSchedule();
  }
}
