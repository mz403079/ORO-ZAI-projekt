package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.*;
import com.orozai.projekt.model.dto.specialized.CommentLikeFormDTO;
import com.orozai.projekt.model.dto.specialized.PostLikeFormDTO;
import com.orozai.projekt.model.service.CommentLikeServiceImpl;
import com.orozai.projekt.model.service.CommentServiceImpl;
import com.orozai.projekt.model.service.ImageServiceImpl;
import com.orozai.projekt.model.service.PostLikeServiceImpl;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.PostTagServiceImpl;
import com.orozai.projekt.model.service.TagServiceImpl;
import com.orozai.projekt.model.service.UserServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerTest {

  private final ModelMapper modelMapper;
  private final PostServiceImpl postService;
  private final PostTagServiceImpl postTagService;
  private final UserServiceImpl userService;
  private final CommentServiceImpl commentService;
  private final ImageServiceImpl imageService;
  private final PostLikeServiceImpl postLikeService;
  private final CommentLikeServiceImpl commentLikeService;

  public ControllerTest(ModelMapper modelMapper, PostServiceImpl postService,
      PostTagServiceImpl postTagService,
      UserServiceImpl userService,
      CommentServiceImpl commentService,
      ImageServiceImpl imageService,
      PostLikeServiceImpl postLikeService,
      CommentLikeServiceImpl commentLikeService) {
    this.modelMapper = modelMapper;
    this.postService = postService;
    this.postTagService = postTagService;
    this.userService = userService;
    this.commentService = commentService;
    this.imageService = imageService;
    this.postLikeService = postLikeService;
    this.commentLikeService = commentLikeService;
  }


  @GetMapping(value = "/getPostTag")
  public ResponseEntity<Collection<PostTagDTO>> getPostTags() {
    Collection<PostTagDTO> tags = postTagService.getAll();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @PostMapping(value ="/likeComment")
  public ResponseEntity<CommentLikeDTO> likePost(@RequestBody CommentLikeFormDTO commentLikeFormDTO) {
    CommentLikeDTO commentLikeDTO = modelMapper.map(commentLikeFormDTO, CommentLikeDTO.class);
    commentLikeDTO.setUser(userService.get(commentLikeFormDTO.getUserId()));
    commentLikeDTO.setComment(commentService.get(commentLikeFormDTO.getCommentId()));
    commentLikeService.handleComment(commentLikeDTO);
    Collection<UserCountDTO> user = postService.getTopUserIds();
    userService.setUsers(user);
    return new ResponseEntity<>(commentLikeDTO,HttpStatus.OK);
  }

  @GetMapping(value = "/getTopUsers")
  public ResponseEntity<Collection<UserCountDTO>> getTopUsers() {
    Collection<UserCountDTO> tops = postService.getTopUserIds();
    userService.setUsers(tops);
    return new ResponseEntity<>(tops, HttpStatus.OK);
  }
  @PostMapping(value ="/likePost")
  public ResponseEntity<PostLikeDTO> likePost(@RequestBody PostLikeDTO postLikeDTO) {
    if(postLikeService.handleLike(postLikeDTO))
      postService.update(postLikeDTO.getPostId(),1);
    else
      postService.update(postLikeDTO.getPostId(),-1);
    return new ResponseEntity<>(postLikeDTO,HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value="/getAdminBoard")
  public ResponseEntity<String> getAdminBoard() {
    return new ResponseEntity<>("essa",HttpStatus.OK);

  }

  @GetMapping("/getImage/{id}")
  public ResponseEntity<ImageDTO> getImage(@PathVariable("id") Long id) {
    ImageDTO imageDTO = imageService.get(id);
    return new ResponseEntity<>(imageDTO,HttpStatus.OK);
  }
}
