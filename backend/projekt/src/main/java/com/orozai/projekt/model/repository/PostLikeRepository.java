package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.UserPostLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<UserPostLiked, Long> {

}
