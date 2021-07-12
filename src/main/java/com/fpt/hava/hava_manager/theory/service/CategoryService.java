package com.fpt.hava.hava_manager.theory.service;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.domain.TheoryDTO;
import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.web.api.hava_manager.theory.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
  CategoryEntity getCatById(Integer id);
  Optional<CategoryEntity> findById(Integer id);
  CategoryEntity findByTitle(String title);
  List<CategoryEntity> findAllByParentId(String id);
}
