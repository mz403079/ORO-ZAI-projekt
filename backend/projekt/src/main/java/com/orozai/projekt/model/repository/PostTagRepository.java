package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.Tag;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
  Collection<PostTag> findAllByTag(Tag tag);
}
