package com.fpt.hava.hava_manager.test_history.domain;

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

@Getter
@Setter
@Entity
@Table(name = "test_histories", schema = "test", catalog = "")
public class TestHistoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "history_id", nullable = false)
  private int historyId;

  @Column(name = "question_id", nullable = false)
  private int questionId;

  @Column(name = "answer", nullable = false)
  private int answer;

}
