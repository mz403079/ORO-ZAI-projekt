package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.Tag;
import java.util.Collection;

import com.orozai.projekt.model.entity.TagCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
  Collection<PostTag> findAllByTag(Tag tag);

  @Query(nativeQuery = true, value =
          "SELECT tag_tag_id as tagId,COUNT(tag_tag_id) as totalCount FROM post_tag " +
          "GROUP BY post_tag.tag_tag_id " +
          "ORDER BY totalCount DESC " +
          "LIMIT 20")
  Collection<ITagCount> getManu();
}
