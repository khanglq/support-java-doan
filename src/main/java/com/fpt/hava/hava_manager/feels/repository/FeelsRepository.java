package com.fpt.hava.hava_manager.feels.repository;

import com.fpt.hava.hava_manager.feels.domain.FeelsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeelsRepository extends JpaRepository<FeelsEntity, Integer> {
  List<FeelsEntity> findTop5ByOrderByIdDesc();
}
