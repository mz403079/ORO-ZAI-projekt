package com.orozai.projekt.model.dto.specialized;

import com.orozai.projekt.model.dto.basic.PostDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageablePostDTO {
  private List<PostDTO> posts = new ArrayList<>();

  private int pages;
}
