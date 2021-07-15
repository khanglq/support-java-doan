package com.fpt.hava.hava_manager.test_history.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "histories")
public class HistoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "user_id", nullable = false)
  private int userId;

  @Column(name = "exam_id", nullable = false)
  private int examId;

  @Column(name = "exam_category_id", nullable = false)
  private int examCategoryId;

  @Column(name = "type", nullable = false)
  private int type;

  @Column(name = "true", nullable = false)
  private int true_answer;

  @Column(name = "test_schedules_id", nullable = false)
  private int testSchedulesId;

  @Column(name = "total_question", nullable = false)
  private int totalQuestion;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
