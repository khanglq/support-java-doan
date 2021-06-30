package com.fpt.hava.hava_manager.feels.service.FeelImpl;

import com.fpt.hava.hava_manager.feels.domain.FeelsEntity;
import com.fpt.hava.hava_manager.feels.repository.FeelsRepository;
import com.fpt.hava.hava_manager.feels.service.FeelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeelImpl implements FeelService {

  private final FeelsRepository feelsRepository;

  @Override
  public List<FeelsEntity> getTop5Feel() {
    List<FeelsEntity> feels = feelsRepository.findTop5ByOrderByIdDesc();
    return feels;
  }
}
