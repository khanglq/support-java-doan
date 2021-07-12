package com.fpt.hava.hava_manager.theory.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class TheoryDTO {
  private Integer id;

  private String title;

  private Integer examId;

  private String contents;
}
