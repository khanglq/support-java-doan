package com.fpt.hava.hava_manager.theory.repository;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

  List<CategoryEntity> findAllByParentId(String id);
  Optional<CategoryEntity> findById(Integer id);

  @Query(value = "select * from categories \n"
      + "WHERE title = ?1 AND description != ''", nativeQuery = true)
  CategoryEntity findByTitle(String title);
}
