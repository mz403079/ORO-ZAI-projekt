package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long userId;

  private String username;
  @JsonIgnore
  private String password;

  private String email;
}
