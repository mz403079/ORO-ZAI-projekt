package com.orozai.projekt.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long commentId;

  @Column(nullable = false, length = 250)
  private String comment;

  private int score;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timeCommented;

  @ManyToOne(optional = false)
  private Post post;

  @OneToOne(optional = false)
  private User author;
}
