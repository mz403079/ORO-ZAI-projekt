package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostTag;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
  Collection<Post> findByTagsIn(Collection<PostTag> postTags);
  Collection<Post> findAllByPostAuthorUserId(long userId);
}
