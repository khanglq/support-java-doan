package com.fpt.hava.hava_manager.theory.service;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
  CategoryEntity getCatById(Integer id);
  List<CategoryEntity> getCatByParentId(String id);
  Optional<CategoryEntity> findById(Integer id);
  CategoryEntity findByTitle(String title);
}
