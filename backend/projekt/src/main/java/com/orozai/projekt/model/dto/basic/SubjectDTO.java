package com.orozai.projekt.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {

  private Long subjectId;

  private String subjectName;

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof SubjectDTO)) {
      return false;
    }
    return this.subjectId.equals(((SubjectDTO) object).subjectId);
  }

  @Override
  public int hashCode() {
    return subjectId.hashCode();
  }
}
