package com.orozai.projekt.controller.tag;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.TagCountDTO;
import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.PostTagServiceImpl;
import com.orozai.projekt.model.service.TagServiceImpl;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TagController {
  private final TagServiceImpl tagService;
  private final PostTagServiceImpl postTagService;

  public TagController(TagServiceImpl tagService,
      PostTagServiceImpl postTagService) {
    this.tagService = tagService;
    this.postTagService = postTagService;

  }

  @GetMapping(value = "/getTopTags")
  public ResponseEntity<Collection<TagCountDTO>> getTopTags() {
    Collection<TagCountDTO> tops = postTagService.getTop();
    tagService.setTags(tops);
    return new ResponseEntity<>(tops, HttpStatus.OK);
  }

  @GetMapping(value = "/getTags")
  public ResponseEntity<Collection<TagDTO>> getTags() {
    Collection<TagDTO> tags = tagService.getAll();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }
}
