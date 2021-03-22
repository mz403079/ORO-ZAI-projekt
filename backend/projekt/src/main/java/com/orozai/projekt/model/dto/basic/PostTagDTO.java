package com.orozai.projekt.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTagDTO {
  private Long postTagId;

  private Long postId;

  private Long tagId;
}
