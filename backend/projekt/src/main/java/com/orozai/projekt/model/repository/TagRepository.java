package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Tag;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {

  @Query(nativeQuery = true, value =
      "SELECT tags_tag_id as Id,COUNT(tags_tag_id) as Count FROM post_tags " +
          "GROUP BY tags_tag_id " +
          "ORDER BY Count DESC " +
          "LIMIT 20")
  Collection<ICount> getTopTags();
}
