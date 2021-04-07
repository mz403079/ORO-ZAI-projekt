package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orozai.projekt.model.dto.specialized.CommentToUserDTO;
import com.orozai.projekt.model.entity.Comment;
import com.orozai.projekt.model.entity.User;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
  private Long postId;

  private String title;

  private String content;

  private int rating;

  private User author;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm")
  private LocalDateTime timePosted;

  private Set<TagDTO> tags = new HashSet<>();

  private Set<CommentDTO> comments = new HashSet<>();

  private String imageData;
}
