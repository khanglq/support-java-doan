package com.fpt.hava.hava_manager.test_history.repository;

import com.fpt.hava.hava_manager.test_history.domain.HistoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {
  HistoryEntity findAllById(Integer id);

  @Query(value = "SELECT COUNT(q.category_id)\n"
      + "FROM `questions` as q INNER JOIN exam_questions as eq\n"
      + "WHERE q.id = eq.question_id AND eq.exam_id = ?1 AND q.category_id = ?2 "
      + "AND q.answer_true != 0", nativeQuery = true)
  Integer totalQuesByCat(Integer idExam, Integer idCat);

  HistoryEntity findTopByOrderByIdDesc();
}
