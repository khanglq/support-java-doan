package com.fpt.hava.hava_manager.theory.service;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import java.util.List;

public interface CategoryService {
  CategoryEntity getCatById(Integer id);
  List<CategoryEntity> getCatByParentId(String id);
}
