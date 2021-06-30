package com.fpt.hava.hava_manager.theory.repository;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoryRepository extends JpaRepository<TheoryEntity, Integer> {
  TheoryEntity findAllById(Integer id);
}
