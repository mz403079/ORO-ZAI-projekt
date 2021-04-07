package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.CommentDTO;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.dto.specialized.PostFormDTO;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements IService<PostDTO> {
  private final ModelMapper modelMapper;
  private final PostRepository postRepository;
  private final TagRepository tagRepository;
  private final PostTagRepository postTagRepository;
  private final UserRepository userRepository;

  public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository,
      TagRepository tagRepository,
      PostTagRepository postTagRepository,
      UserRepository userRepository) {
    this.modelMapper = modelMapper;
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
    this.postTagRepository = postTagRepository;
    this.userRepository = userRepository;
  }
  @Override
  @Transactional
  public PostDTO get(Long id) {
    Post post = postRepository.findById(id).orElseThrow(DataNotFoundException::new);
    return postToPostDTO(post);
  }


  @Override
  @Transactional
  public Collection<PostDTO> getAll() {
    Collection<Post> posts = postRepository.findAll();
    Collection<PostDTO> postsDTO = modelMapper.map(posts, new TypeToken<Set<PostDTO>>(){}.getType());
    postsDTO.clear();
    for(Post post : posts) {
      postsDTO.add(postToPostDTO(post));
    }

    return postsDTO;
  }

  @Override
  public PostDTO create(PostDTO postDTO) {
    return null;
  }

  public Collection<PostDTO> getByTagId(Long id) {
    return modelMapper.map(postRepository.findByTagsIn(postTagRepository.findAllByTag(tagRepository.findById(id).get())),
        new TypeToken<Set<PostDTO>>(){}.getType());
  }

  public PostDTO create(String title, String content, MultipartFile file, int[] tags, int authorId) {
    Post p = new Post();
    if (file != null) {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      if (fileName.contains("..")) {
        System.out.println("not a a valid file");
      }
      try {
        p.setImageData(Base64.getEncoder().encode(file.getBytes()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (content != null) {
      p.setContent(content);
    }
    p.setTitle(title);

    p.setPostAuthor(userRepository.findById((long) authorId).orElseThrow(
        DataNotFoundException::new));
    p.setLink(null);
    p.setTimePosted(LocalDateTime.now());
    postRepository.save(p);
    for (int x : tags) {
      PostTag postTag = new PostTag();
      postTag.setPost(p);
      postTag.setTag(tagRepository.findById((long)x).orElseThrow(DataNotFoundException::new));
      postTagRepository.save(postTag);
    }
    return null;

  }

  @Override
  public PostDTO update(PostDTO postDTO) {
    return null;
  }

  @Override
  public void delete(PostDTO postDTO) {

  }

  public PostDTO postToPostDTO(Post post) {
    PostDTO postDTO = modelMapper.map(post,PostDTO.class);
    byte[] imageData = post.getImageData();
    if(imageData != null && imageData.length > 0)
      postDTO.setImageData(new String(imageData));
    else
      postDTO.setImageData(null);
    return postDTO;
  }
}
