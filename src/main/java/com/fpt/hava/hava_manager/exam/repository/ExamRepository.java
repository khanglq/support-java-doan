package com.fpt.hava.hava_manager.exam.repository;

import com.fpt.hava.hava_manager.exam.domain.ExamsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamsEntity, Integer> {

  List<ExamsEntity> findAllByCategoryId(Integer id);
}
