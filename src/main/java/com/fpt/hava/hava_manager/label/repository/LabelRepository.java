package com.fpt.hava.hava_manager.label.repository;

import com.fpt.hava.hava_manager.feels.domain.FeelsEntity;
import com.fpt.hava.hava_manager.label.domain.LabelsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<LabelsEntity, Integer> {
  Optional<LabelsEntity> findById(Integer id);
}
