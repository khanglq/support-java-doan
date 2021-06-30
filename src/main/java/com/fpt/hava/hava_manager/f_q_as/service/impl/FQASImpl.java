package com.fpt.hava.hava_manager.f_q_as.service.impl;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import com.fpt.hava.hava_manager.document.repository.DocumentRepository;
import com.fpt.hava.hava_manager.f_q_as.domain.FQAsEntity;
import com.fpt.hava.hava_manager.f_q_as.repository.FQASRepository;
import com.fpt.hava.hava_manager.f_q_as.service.FQASService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FQASImpl implements FQASService {

  private final FQASRepository fqasRepository;

  @Override
  public List<FQAsEntity> getAllFQAS() {
    List<FQAsEntity> fqAsEntities = fqasRepository.findAll();
    return fqAsEntities;
  }
}
