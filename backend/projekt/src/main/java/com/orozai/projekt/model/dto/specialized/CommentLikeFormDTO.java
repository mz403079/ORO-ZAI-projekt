package com.orozai.projekt.model.dto.specialized;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeFormDTO {
  private long userId;

  private long commentId;
}
