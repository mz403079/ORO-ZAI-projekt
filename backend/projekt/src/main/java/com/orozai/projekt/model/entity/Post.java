package com.orozai.projekt.model.entity;

import com.orozai.projekt.model.dto.basic.TagDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;


import java.time.LocalTime;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  @Column(length = 1000)
  @Type(type = "text")
  private String content;

  private String link;

  private int score;

  @OneToOne
  private User author;

  private LocalDateTime timePosted;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<PostTag> tags = new HashSet<>();

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Comment> comments = new HashSet<>();

  @Lob
  private String imageData;

}
