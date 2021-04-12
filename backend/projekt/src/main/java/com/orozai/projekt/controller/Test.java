package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mega")
public class Test {
  private final PostServiceImpl postService;

  public Test(PostServiceImpl postService) {
    this.postService = postService;
  }

  @GetMapping(value = "/post/{id}")
  public ResponseEntity<PostDTO> getPost(@PathVariable("id") Long id) {
    PostDTO post = postService.get(id);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }
}