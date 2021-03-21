package com.orozai.projekt.model.dto.basic;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
  private Long postId;

  private String title;

  private String content;

  private int rating;

  private long authorId;

  private LocalTime timePosted;

  @OneToMany(mappedBy = "PostDTO")
  private Set<TagDTO> tags = new HashSet<>();
}
