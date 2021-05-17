package com.orozai.projekt.model.dto.specialized;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeFormDTO {
  private long userPostLikeId;

  private long userId;

  private long postId;

}
