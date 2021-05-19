package com.orozai.projekt.controller.post;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.service.PostServiceImpl;
import com.orozai.projekt.model.service.TagServiceImpl;
import java.util.Collection;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PostController {

  private final PostServiceImpl postService;
  private final TagServiceImpl tagService;

  public PostController(PostServiceImpl postService,
      TagServiceImpl tagService) {
    this.postService = postService;
    this.tagService = tagService;
  }

  @GetMapping(value = "/getPosts")
  public ResponseEntity<Collection<PostDTO>> getPosts() {
    Collection<PostDTO> posts = postService.getAll();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping(value = "/getProfilePosts/{id}")
  public ResponseEntity<Collection<PostDTO>> getProfilePosts(@PathVariable("id") Long id) {
    Collection<PostDTO> posts = postService.getProfilePosts(id);
    return new ResponseEntity<>(posts, HttpStatus.OK);

  }

  @GetMapping(value = "/getLikedPosts/{id}")
  public ResponseEntity<Collection<PostDTO>> getLikedPosts(@PathVariable("id") Long id) {
    Collection<PostDTO> posts = postService.getLikedPosts(id);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping(value = "/getUserPosts/{username}")
  public ResponseEntity<Collection<PostDTO>> getUserPosts(
      @PathVariable("username") String username) {
    Collection<PostDTO> posts = postService.getByUserUsername(username);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping(value = "/getTopPosts")
  public ResponseEntity<Collection<PostDTO>> getTopPosts() {
    Collection<PostDTO> posts = postService.getTopLikedPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping(value = "/tag/{id}")
  public ResponseEntity<Collection<PostDTO>> getPostByTagId(@PathVariable("id") Long id) {
    Collection<PostDTO> posts = postService.getByTagId(id);
    if (posts.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(posts, HttpStatus.OK);
    }
  }

  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  @GetMapping(value = "/post/{id}")
  public ResponseEntity<PostDTO> getPost(@PathVariable("id") Long id) {
    System.out
        .println("ENIU" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    PostDTO post = postService.get(id);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @PostMapping(value = "/addPost")
  public ResponseEntity<PostDTO> addPost(@RequestParam("title") String title,
      @RequestParam(name = "content", required = false) String content,
      @RequestParam(name = "file", required = false) MultipartFile file,
      @RequestParam("tags") int[] tagIds,
      @RequestParam("authorId") int authorId) {
    Set<Tag> tags = tagService.assignTagnames(tagIds);
    postService.create(title, content, file, tags, authorId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
