package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.CommentDTO;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostTagDTO;
import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.dto.specialized.CommentFormDTO;
import com.orozai.projekt.model.service.CommentServiceImpl;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.PostTagServiceImpl;
import com.orozai.projekt.model.service.TagServiceImpl;
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
  private final UserServiceImpl userService;
  private final CommentServiceImpl commentService;
  public ControllerTest(ModelMapper modelMapper, PostServiceImpl postService,
      TagServiceImpl tagService, PostTagServiceImpl postTagService,
      UserServiceImpl userService,
      CommentServiceImpl commentService) {
    this.modelMapper = modelMapper;
    this.postService = postService;
    this.tagService = tagService;
    this.postTagService = postTagService;
    this.userService = userService;
    this.commentService = commentService;
  }

  @GetMapping(value = "/getPosts")
  public ResponseEntity<Collection<PostDTO>> getPosts() {
    Collection<PostDTO> posts = postService.getAll();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }
  @GetMapping(value = "/getComments")
  public ResponseEntity<Collection<CommentDTO>> getComments() {
    Collection<CommentDTO> comments = commentService.getAll();
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  @GetMapping(value = "/post/{id}")
  public ResponseEntity<PostDTO> getPost(@PathVariable("id") Long id) {
    PostDTO post = postService.get(id);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }
  @GetMapping(value = "/getTags")
  public ResponseEntity<Collection<TagDTO>> getTags() {
    Collection<TagDTO> tags = tagService.getAll();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @GetMapping(value = "/getPostTag")
  public ResponseEntity<Collection<PostTagDTO>> getPostTags() {
    Collection<PostTagDTO> tags = postTagService.getAll();
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

  @PostMapping(value ="/addComment")
  public ResponseEntity<CommentDTO> addComment(@RequestBody CommentFormDTO commentFormDTO) {
    CommentDTO commentDTO = modelMapper.map(commentFormDTO, CommentDTO.class);
    commentDTO.setCommentAuthor(userService.get(commentFormDTO.getAuthorId()));
    commentDTO.setPost(postService.get(commentFormDTO.getPostId()));
    System.out.println("HIre"+commentFormDTO.getContent()+commentDTO.getContent());
    commentService.create(commentDTO);
    return new ResponseEntity<>(commentDTO,HttpStatus.OK);
  }
}
