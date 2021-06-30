package com.fpt.hava.hava_manager.feels.service;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import com.fpt.hava.hava_manager.feels.domain.FeelsEntity;
import java.util.List;

public interface FeelService {
  List<FeelsEntity> getTop5Feel();
}
