package com.fpt.hava.hava_manager.theory.service.impl;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.domain.TheoryDTO;
import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.hava_manager.theory.repository.CategoryRepository;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.web.api.hava_manager.theory.dto.CategoryDTO;
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
  public Optional<CategoryEntity> findById(Integer id) {
    return categoryRepository.findById(id);
  }

  @Override
  public CategoryEntity findByTitle(String title) {
    return categoryRepository.findByTitle(title);
  }

  @Override
  public List<CategoryEntity> findAllByParentId(String id) {
    return categoryRepository.findAllByParentId(id);
  }
}
