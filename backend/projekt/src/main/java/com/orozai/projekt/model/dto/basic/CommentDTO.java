package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

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
