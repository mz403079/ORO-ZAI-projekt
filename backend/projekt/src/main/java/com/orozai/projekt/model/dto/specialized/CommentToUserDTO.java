package com.orozai.projekt.model.dto.specialized;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentToUserDTO {
  private Long commentId;

  private String content;

  private int score;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm")
  private LocalDateTime timeCommented;

  private Long postId;

  private Long authorId;
}
