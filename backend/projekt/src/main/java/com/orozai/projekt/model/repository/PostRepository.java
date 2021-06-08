package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findAllByPostAuthorUserId(long userId);

  Page<Post> findAllByPostAuthorUsername(String username, Pageable pageable);

  Page<Post> findAllByTagsTagId(long id, Pageable pageable);

  Collection<Post> findByTitleContainingOrContentContainingOrPostAuthorUsernameContaining(
      String query, String queryContent, String name);

  @Query(nativeQuery = true, value =
      "SELECT post_author_user_id as Id,COUNT(post_author_user_id) as Count FROM post " +
          "GROUP BY post_author_user_id " +
          "ORDER BY Count DESC " +
          "LIMIT 20")
  Collection<ICount> getTopUserIds();


}
