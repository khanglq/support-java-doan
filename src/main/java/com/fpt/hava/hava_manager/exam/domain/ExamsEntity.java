package com.fpt.hava.hava_manager.exam.domain;

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
@Table(name = "exams")
public class ExamsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false, length = 191)
  private String title;

  @Column(name = "category_id", nullable = false)
  private int categoryId;

  @Column(name = "is_random", nullable = false)
  private int isRandom;

  @Column(name = "is_done", nullable = false)
  private short isDone;

  @Column(name = "is_free", nullable = false)
  private int isFree;

  @Column(name = "is_theory", nullable = false)
  private int isTheory;

  @Column(name = "is_private", nullable = false)
  private byte isPrivate;

  @Column(name = "level", nullable = false)
  private int level;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
