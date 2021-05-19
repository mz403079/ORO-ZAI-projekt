package com.orozai.projekt.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeDTO {

  private long CommentLikeId;

  private UserDTO user;

  private CommentDTO comment;
}
