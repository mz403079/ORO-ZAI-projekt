package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.entity.PostLike;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long userId;

  private String username;
  @JsonIgnore
  private String password;
  @JsonIgnore
  private String email;
  @JsonIgnore
  private Set<CommentDTO> comments = new HashSet<>();

  private ImageDTO profileImage;
}
