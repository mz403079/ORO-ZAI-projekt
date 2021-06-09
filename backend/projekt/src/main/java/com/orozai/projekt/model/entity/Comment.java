package com.orozai.projekt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long commentId;

  @Column(nullable = false, length = 250)
  private String content;

  private int score;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timeCommented;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Post post;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JsonIgnore
  private User commentAuthor;

  public void removeComment(Post comment) {
    this.getPost().getComments().remove(comment);
    this.setPost(null);
  }
}
