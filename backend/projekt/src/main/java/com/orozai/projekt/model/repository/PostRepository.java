package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
