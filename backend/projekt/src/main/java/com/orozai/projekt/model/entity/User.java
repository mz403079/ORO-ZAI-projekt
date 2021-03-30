package com.orozai.projekt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="\"user\"")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long userId;

  @Column(nullable = false, length = 50, unique = true)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column(nullable = false, unique = true)
  @JsonIgnore
  private String email;
}
