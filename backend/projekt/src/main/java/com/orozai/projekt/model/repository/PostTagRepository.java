package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.Tag;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
  Collection<PostTag> findAllByTag(Tag tag);

  @Query(nativeQuery = true, value =
          "SELECT tag_tag_id as Id,COUNT(tag_tag_id) as Count FROM post_tag " +
          "GROUP BY tag_tag_id " +
          "ORDER BY Count DESC " +
          "LIMIT 20")
  Collection<ICount> getTopTags();
}
