package com.fpt.hava.hava_manager.document.service;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import java.util.List;

public interface DocumentService {
  List<DocumentsEntity> findByDocumentCategoryId(Integer id);
  List<DocumentsEntity> findAllDocuments();
}
