package com.fpt.hava.hava_manager.test_shedule.repository;

import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import com.fpt.hava.hava_manager.test_shedule.domain.TestSchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestScheduleRepository extends JpaRepository<TestSchedulesEntity, Integer> {

  @Query(value = "SELECT * FROM `test_schedules` \n"
      + "ORDER BY `begin` DESC LIMIT 1", nativeQuery = true)
  TestSchedulesEntity findLastSchedule();

}
