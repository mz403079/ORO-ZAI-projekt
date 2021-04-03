package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.dto.specialized.PostFormDTO;
import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.PostTagServiceImpl;
import com.orozai.projekt.model.service.TagServiceImpl;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ControllerTest {

  private final ModelMapper modelMapper;
  private final PostServiceImpl postService;
  private final TagServiceImpl tagService;
  private final PostTagServiceImpl postTagService;
  public ControllerTest(ModelMapper modelMapper, PostServiceImpl postService,
      TagServiceImpl tagService, PostTagServiceImpl postTagService) {
    this.modelMapper = modelMapper;
    this.postService = postService;
    this.tagService = tagService;
    this.postTagService = postTagService;
  }

  @GetMapping(value = "/getPosts")
  public ResponseEntity<Collection<PostDTO>> getPosts() {
    Collection<PostDTO> posts = postService.getAll();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping(value = "/getTags")
  public ResponseEntity<Collection<TagDTO>> getTags() {
    Collection<TagDTO> tags = tagService.getAll();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @GetMapping(value ="/tag/{id}")
  public ResponseEntity<Collection<PostDTO>> getPostByTagId(@PathVariable("id") Long id) {
    Collection<PostDTO> posts = postService.getByTagId(id);
    if(posts.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else
      return new ResponseEntity<>(posts,HttpStatus.OK);
  }

  @PostMapping(value ="/addPost")
  public ResponseEntity<PostDTO> addPost(@RequestParam("title") String title,
      @RequestParam(name="content",required = false) String content,
      @RequestParam(name="file",required = false) MultipartFile file,
      @RequestParam("tags") int[] tags,
      @RequestParam("authorId") int authorId) {
    postService.create(title,content,file,tags,authorId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
