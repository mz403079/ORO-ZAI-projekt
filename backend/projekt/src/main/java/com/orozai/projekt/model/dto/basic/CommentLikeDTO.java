package com.orozai.projekt.model.dto.basic;

import com.orozai.projekt.model.entity.Comment;
import com.orozai.projekt.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeDTO {

  private long CommentLikeId;

  private UserDTO user;

  private CommentDTO comment;
}
