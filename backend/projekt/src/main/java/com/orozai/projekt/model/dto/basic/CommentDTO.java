package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
  private Long commentId;

  private String content;

  private int score;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm")
  private LocalDateTime timeCommented;
  @JsonIgnore
  private PostDTO post;

  private UserDTO commentAuthor;
}
