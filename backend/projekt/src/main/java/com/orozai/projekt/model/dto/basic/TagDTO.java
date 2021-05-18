package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orozai.projekt.model.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDTO {
  private Long tagId;

  private String tagName;
}
