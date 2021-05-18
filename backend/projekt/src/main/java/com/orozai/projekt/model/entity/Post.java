package com.orozai.projekt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

  @OneToOne
  private User postAuthor;

  @Column(nullable = false)
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timePosted;

  @ManyToMany(mappedBy = "likedPosts")
  private Set<User> likes = new HashSet<>();

  @JoinTable
  @ManyToMany
  private Set<Tag> tags = new HashSet<>();

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Comment> comments = new HashSet<>();
  
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Image postImage;

  public int getNumOfLikes() {
    return likes.size();
  }

}
