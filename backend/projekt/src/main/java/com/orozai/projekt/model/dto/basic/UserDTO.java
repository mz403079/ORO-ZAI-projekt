package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
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
  @JsonIgnore
  private Set<PostDTO> likedPosts = new HashSet<>();

  private ImageDTO profileImage;
}
