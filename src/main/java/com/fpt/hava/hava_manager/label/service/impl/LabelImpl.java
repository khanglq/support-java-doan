package com.fpt.hava.hava_manager.label.service.impl;

import com.fpt.hava.hava_manager.label.domain.LabelsEntity;
import com.fpt.hava.hava_manager.label.repository.LabelRepository;
import com.fpt.hava.hava_manager.label.service.LabelService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelImpl implements LabelService {

  private final LabelRepository labelRepository;

  @Override
  public Optional<LabelsEntity> getLabelById(Integer id) {
    Optional<LabelsEntity> labelsEntity = labelRepository.findById(id);
    
    return labelsEntity;
  }
}
