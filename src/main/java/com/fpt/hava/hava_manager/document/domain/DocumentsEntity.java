package com.fpt.hava.hava_manager.document.domain;

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
@Table(name = "documents")
public class DocumentsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "title", nullable = false, length = 191)
  private String title;

  @Column(name = "thumbnail", nullable = false, length = 191)
  private String thumbnail;

  @Column(name = "href", nullable = false, length = 191)
  private String href;

  @Column(name = "document_category_id", nullable = false)
  private int documentCategoryId;

  @Column(name = "lock", nullable = false)
  private short lock;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
