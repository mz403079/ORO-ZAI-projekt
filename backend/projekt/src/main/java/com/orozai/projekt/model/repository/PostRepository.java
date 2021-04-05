package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.Tag;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
  Collection<Post> findByTagsIn(Collection<PostTag> postTags);
}
