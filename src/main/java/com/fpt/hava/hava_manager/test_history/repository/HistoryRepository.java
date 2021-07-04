package com.fpt.hava.hava_manager.test_history.repository;

import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {

}
