package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
