package com.orozai.projekt.model.entity;

import com.orozai.projekt.model.dto.basic.TagDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;


import java.time.LocalTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  private String title;

  private String content;

  private int rating;

  private Long authorId;

  private LocalTime timePosted;

  @OneToMany(mappedBy = "post")
  private Set<PostTag> tags = new HashSet<>();
}
