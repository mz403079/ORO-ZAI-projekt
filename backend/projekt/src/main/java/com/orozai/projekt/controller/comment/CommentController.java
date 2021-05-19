package com.orozai.projekt.controller.comment;

import com.orozai.projekt.model.dto.basic.CommentDTO;
import com.orozai.projekt.model.dto.specialized.CommentFormDTO;
import com.orozai.projekt.model.service.CommentServiceImpl;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.UserServiceImpl;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentController {

  private final ModelMapper modelMapper;
  private final UserServiceImpl userService;
  private final CommentServiceImpl commentService;
  private final PostServiceImpl postService;

  public CommentController(ModelMapper modelMapper,
      UserServiceImpl userService,
      CommentServiceImpl commentService,
      PostServiceImpl postService) {
    this.modelMapper = modelMapper;
    this.userService = userService;
    this.commentService = commentService;
    this.postService = postService;
  }

  @PostMapping(value = "/addComment")
  public ResponseEntity<CommentDTO> addComment(@RequestBody CommentFormDTO commentFormDTO) {
    CommentDTO commentDTO = modelMapper.map(commentFormDTO, CommentDTO.class);
    commentDTO.setCommentAuthor(userService.get(commentFormDTO.getAuthorId()));
    commentDTO.setPost(postService.get(commentFormDTO.getPostId()));
    commentService.create(commentDTO);
    return new ResponseEntity<>(commentDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/getComments")
  public ResponseEntity<Collection<CommentDTO>> getComments() {
    Collection<CommentDTO> comments = commentService.getAll();
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }
}
