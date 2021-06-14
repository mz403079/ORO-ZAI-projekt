package com.orozai.projekt.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long imageId;

  @Lob
  private byte[] imageData;

  @OneToOne(mappedBy = "postImage")
  private Post post;

}
