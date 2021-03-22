package com.orozai.projekt.model.dto.basic;

import com.orozai.projekt.model.entity.Post;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
  private Long commentId;

  private String comment;

  private int score;

  private Post post;
}
