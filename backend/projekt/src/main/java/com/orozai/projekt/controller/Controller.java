package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostTagDTO;
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
public class Controller {

  private final PostServiceImpl postService;
  private final TagServiceImpl tagService;
  private final PostTagServiceImpl postTagService;
  public Controller(PostServiceImpl postService,
      TagServiceImpl tagService, PostTagServiceImpl postTagService) {
    this.postService = postService;
    this.tagService = tagService;
    this.postTagService = postTagService;
  }

  @GetMapping(value = "/getPosts")
  public ResponseEntity<Collection<PostDTO>> getPosts() {
    Collection<PostDTO> posts = postService.getAll();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping(value ="/tag/{id}")
  public ResponseEntity<Collection<PostDTO>> getPostByTagId(@PathVariable("id") Long id) {
    Collection<PostDTO> posts = postService.getByTagId(id);
    if(posts.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else
      return new ResponseEntity<>(posts,HttpStatus.OK);
  }
}
