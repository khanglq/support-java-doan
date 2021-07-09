package com.fpt.hava.hava_manager.theory.service.impl;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.repository.CategoryRepository;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public CategoryEntity getCatById(Integer id) {
    CategoryEntity categoryEntity = categoryRepository.getById(id);
    return categoryEntity;
  }

  @Override
  public List<CategoryEntity> getCatByParentId(String id) {
    List<CategoryEntity> categoryEntities = categoryRepository.findAllByParentId(id);
    return categoryEntities;
  }

  @Override
  public Optional<CategoryEntity> findById(Integer id) {
    return categoryRepository.findById(id);
  }
}
