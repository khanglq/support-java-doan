package com.fpt.hava.hava_manager.theory.domain;

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
@Table(name = "theories")
public class TheoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false, length = 191)
  private String title;

  @Column(name = "contents", nullable = false)
  private String contents;

  @Column(name = "exam_id", nullable = false)
  private int examId;

  @Column(name = "category_id", nullable = false)
  private int categoryId;

  @Column(name = "category_path", nullable = false, length = 191)
  private String categoryPath;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
