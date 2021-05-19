package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagCountDTO {

  @JsonIgnore
  Long Id;

  Long Count;

  TagDTO tag;
}
