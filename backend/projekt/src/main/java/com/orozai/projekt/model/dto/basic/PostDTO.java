package com.orozai.projekt.model.dto.basic;

import com.orozai.projekt.model.entity.User;
import java.time.LocalTime;
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

  private LocalTime timePosted;

  private Set<TagDTO> tags = new HashSet<>();

  private String imageData;
}
