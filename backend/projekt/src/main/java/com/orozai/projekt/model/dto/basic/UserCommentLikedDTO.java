package com.orozai.projekt.model.dto.basic;

import com.orozai.projekt.model.entity.Comment;
import com.orozai.projekt.model.entity.User;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommentLikedDTO {

  private long userCommentLikeId;

  private User user;


  private Comment comment;
}
