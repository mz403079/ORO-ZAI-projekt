package com.orozai.projekt.model.dto.basic;

import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeDTO {

  private long userPostLikeId;

  private User user;


  private Post post;
}
