package com.fpt.hava.hava_manager.theory.repository;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

  List<CategoryEntity> findAllByParentId(String id);
  Optional<CategoryEntity> findById(Integer id);
}
