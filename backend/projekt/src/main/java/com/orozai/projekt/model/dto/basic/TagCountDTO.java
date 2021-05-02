package com.orozai.projekt.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orozai.projekt.model.entity.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagCountDTO {
    @JsonIgnore
    Long tagId;

    Long totalCount;

    TagDTO tag;
}
