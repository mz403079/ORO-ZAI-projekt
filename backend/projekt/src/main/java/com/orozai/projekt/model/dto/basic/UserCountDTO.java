package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCountDTO {
  @JsonIgnore
  Long Id;

  Long Count;

  UserDTO user;

}
