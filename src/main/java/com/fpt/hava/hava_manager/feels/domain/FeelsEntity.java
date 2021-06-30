package com.fpt.hava.hava_manager.feels.domain;

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
@Table(name = "feels")
public class FeelsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false, length = 191)
  private String title;

  @Column(name = "despication", nullable = false, length = 191)
  private String despication;

  @Column(name = "thumbnail", nullable = false, length = 191)
  private String thumbnail;

  @Column(name = "contents", nullable = false, length = -1)
  private String contents;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
