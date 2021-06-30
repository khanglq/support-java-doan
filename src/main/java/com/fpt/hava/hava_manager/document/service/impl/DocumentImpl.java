package com.fpt.hava.hava_manager.document.service.impl;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import com.fpt.hava.hava_manager.document.repository.DocumentRepository;
import com.fpt.hava.hava_manager.document.service.DocumentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentImpl implements DocumentService {

  private final DocumentRepository documentRepository;

  @Override
  public List<DocumentsEntity> findByDocumentCategoryId(Integer id) {
    List<DocumentsEntity> documents = documentRepository.findByDocumentCategoryId(id);
    return documents;
  }

  @Override
  public List<DocumentsEntity> findAllDocuments() {
    List<DocumentsEntity> documents = documentRepository.findAll();
    return documents;
  }
}
