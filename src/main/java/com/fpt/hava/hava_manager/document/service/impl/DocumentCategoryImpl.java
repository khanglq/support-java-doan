package com.fpt.hava.hava_manager.document.service.impl;

import com.fpt.hava.hava_manager.document.DocumentCategoriesEntity;
import com.fpt.hava.hava_manager.document.repository.DocumentCategoriesRepository;
import com.fpt.hava.hava_manager.document.service.DocumentCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentCategoryImpl implements DocumentCategoryService {

  private final DocumentCategoriesRepository documentCategoriesRepository;

  @Override
  public List<DocumentCategoriesEntity> getAllDocumentCategories() {
    List<DocumentCategoriesEntity> documents = documentCategoriesRepository.findAll();
    return documents;
  }
}
