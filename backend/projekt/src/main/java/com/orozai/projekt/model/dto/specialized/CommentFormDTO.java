package com.orozai.projekt.model.dto.specialized;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDTO {

  private String content;

  private Long postId;

  private Long authorId;

}
