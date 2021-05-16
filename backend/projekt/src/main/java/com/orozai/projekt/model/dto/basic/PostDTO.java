package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orozai.projekt.model.entity.User;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
  private Long postId;

  private String title;

  private String content;

  private int score;

  private UserDTO author;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm")
  private LocalDateTime timePosted;

  private Set<TagDTO> tags = new HashSet<>();

  private Set<CommentDTO> comments = new HashSet<>();

  private int numOfComments;

  private ImageDTO postImage;
}
