package com.fpt.hava.hava_manager.exam.domain;

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
@Table(name = "questions")
public class QuestionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false, length = 191)
  private String title;

  @Column(name = "label_id", nullable = false)
  private int labelId;

  @Column(name = "question", nullable = false)
  private String question;

  @Column(name = "hints", nullable = false)
  private String hints;

  @Column(name = "answer", nullable = false)
  private String answer;

  @Column(name = "comments", nullable = false)
  private String comments;

  @Column(name = "answer_true", nullable = false)
  private int answerTrue;

  @Column(name = "answer_a", nullable = false)
  private String answerA;

  @Column(name = "answer_b", nullable = false)
  private String answerB;

  @Column(name = "answer_c", nullable = false)
  private String answerC;

  @Column(name = "answer_d", nullable = false)
  private String answerD;

  @Column(name = "category_id", nullable = false)
  private int categoryId;

  @Column(name = "level", nullable = false)
  private int level;

  @Column(name = "is_label", nullable = false)
  private int isLabel;

  @Column(name = "label_of", nullable = false)
  private int labelOf;

  @CreationTimestamp
  @Column(name = "created_at")
  private OffsetDateTime createAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
