package com.orozai.projekt.model.dto.specialized;

import com.orozai.projekt.model.dto.basic.TagDTO;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostFormDTO {
  @NotBlank
  @Length(max = 100)
  private String title;

  @NotNull
  private String content;

  @NotNull
  private Long authorId;

  @OneToMany(mappedBy = "PostDTO")
  private ArrayList<TagDTO> tags = new ArrayList<>();
}
