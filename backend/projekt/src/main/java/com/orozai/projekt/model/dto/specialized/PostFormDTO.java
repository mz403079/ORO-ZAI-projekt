package com.orozai.projekt.model.dto.specialized;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostFormDTO {

  @Length(max = 100)
  private String title;

  private String content;

  MultipartFile imageData;
}
