package com.orozai.projekt.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTagForm {
  private Post post;

  private int[] tags;
}
