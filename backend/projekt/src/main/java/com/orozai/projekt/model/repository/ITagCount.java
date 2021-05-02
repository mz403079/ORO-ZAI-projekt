package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.entity.TagCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITagCount {
    Long getTagId();
    Long getTotalCount();
}
