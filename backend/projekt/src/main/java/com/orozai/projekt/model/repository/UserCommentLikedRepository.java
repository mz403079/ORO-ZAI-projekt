package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.entity.UserCommentLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentLikedRepository extends JpaRepository<UserCommentLiked, Long> {

}
