package com.fpt.hava.hava_manager.test_history.repository;

import com.fpt.hava.hava_manager.test_history.domain.TestHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestHistoryRepository extends JpaRepository<TestHistoryEntity, Integer> {

}
