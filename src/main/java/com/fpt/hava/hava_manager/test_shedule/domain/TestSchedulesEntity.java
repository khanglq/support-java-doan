package com.fpt.hava.hava_manager.test_shedule.domain;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Basic;
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
@Table(name = "test_schedules")
public class TestSchedulesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "begin", nullable = false)
  private Timestamp begin;

  @Column(name = "end", nullable = false)
  private Timestamp end;

  @Column(name = "category_id", nullable = false)
  private int categoryId;

  @Column(name = "is_repeat", nullable = false)
  private byte isRepeat;

  @Column(name = "exam_id", nullable = false)
  private int examId;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
