package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Comment;
import com.orozai.projekt.model.entity.CommentLike;
import com.orozai.projekt.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
  Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
