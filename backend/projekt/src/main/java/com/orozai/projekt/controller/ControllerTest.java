package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.CommentLikeDTO;
import com.orozai.projekt.model.dto.basic.ImageDTO;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostLikeDTO;
import com.orozai.projekt.model.dto.basic.UserCountDTO;
import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.dto.specialized.CommentLikeFormDTO;
import com.orozai.projekt.model.service.CommentLikeServiceImpl;
import com.orozai.projekt.model.service.CommentServiceImpl;
import com.orozai.projekt.model.service.ImageServiceImpl;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.UserServiceImpl;
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

  private final PostServiceImpl postService;
  private final UserServiceImpl userService;
  private final ImageServiceImpl imageService;

  public ControllerTest(PostServiceImpl postService,
      UserServiceImpl userService,
      ImageServiceImpl imageService) {
    this.postService = postService;
    this.userService = userService;
    this.imageService = imageService;
  }

  @GetMapping(value = "/getTopUsers")
  public ResponseEntity<Collection<UserCountDTO>> getTopUsers() {
    Collection<UserCountDTO> tops = postService.getTopUserIds();
    userService.setUsers(tops);
    return new ResponseEntity<>(tops, HttpStatus.OK);
  }

  @PostMapping(value = "/likePost")
  public ResponseEntity<PostLikeDTO> likePost(@RequestBody PostLikeDTO postLikeDTO) {
    UserDTO user = userService.get(postLikeDTO.getUserId());
    PostDTO post = postService.get(postLikeDTO.getPostId());
    userService.update(user, post);
    return new ResponseEntity<>(postLikeDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/getAdminBoard")
  public ResponseEntity<String> getAdminBoard() {
    return new ResponseEntity<>("essa", HttpStatus.OK);

  }

  @GetMapping("/getImage/{id}")
  public ResponseEntity<ImageDTO> getImage(@PathVariable("id") Long id) {
    ImageDTO imageDTO = imageService.get(id);
    return new ResponseEntity<>(imageDTO, HttpStatus.OK);
  }
}
