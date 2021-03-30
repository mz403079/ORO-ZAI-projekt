package com.orozai.projekt.model.dto.specialized;

import com.orozai.projekt.model.dto.basic.TagDTO;
import java.util.ArrayList;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
