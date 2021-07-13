package com.fpt.hava.hava_manager.theory.repository;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoryRepository extends JpaRepository<TheoryEntity, Integer> {
  List<TheoryEntity> findAllByCategoryId(Integer id);
  TheoryEntity findAllById(Integer id);
}
