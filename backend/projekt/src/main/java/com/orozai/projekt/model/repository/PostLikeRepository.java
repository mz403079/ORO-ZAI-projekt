package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostLike;
import com.orozai.projekt.model.entity.User;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
  PostLike findByUserIdAndPostId(long userId, long postId);
  Collection<PostLike> findAllByUserId(long userId);
}
