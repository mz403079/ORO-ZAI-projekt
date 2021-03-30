package com.orozai.projekt.model.dto.basic;

import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
  private Long commentId;

  private String comment;

  private int score;

  private Post post;

  private User author;
}
