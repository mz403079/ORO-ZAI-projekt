package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostTag;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

  Collection<Post> findByTagsIn(Collection<PostTag> postTags);

  Collection<Post> findAllByPostAuthorUserId(long userId);
  Collection<Post> findByOrderByScoreDesc();
  @Query(nativeQuery = true, value =
      "SELECT post_author_user_id as Id,COUNT(post_author_user_id) as Count FROM post " +
          "GROUP BY post_author_user_id " +
          "ORDER BY Count DESC " +
          "LIMIT 20")
  Collection<ICount> getTopUserIds();
}
