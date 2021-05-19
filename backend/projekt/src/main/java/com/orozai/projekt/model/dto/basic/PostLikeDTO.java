package com.orozai.projekt.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeDTO {

  private long userPostLikeId;

  private long userId;

  private long postId;
}
