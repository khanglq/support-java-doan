package com.fpt.hava.hava_manager.label.service;

import com.fpt.hava.hava_manager.label.domain.LabelsEntity;
import java.util.Optional;

public interface LabelService {
  Optional<LabelsEntity> getLabelById(Integer id);
}
