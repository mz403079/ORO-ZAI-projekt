package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostTagForm;
import com.orozai.projekt.model.repository.ImageRepository;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collection;
import java.util.Set;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements IService<PostDTO> {
  private final ModelMapper modelMapper;
  private final PostRepository postRepository;
  private final TagRepository tagRepository;
  private final PostTagRepository postTagRepository;
  private final UserRepository userRepository;
  private final PostTagServiceImpl postTagService;
  private final ImageServiceImpl imageService;

  public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository,
      TagRepository tagRepository,
      PostTagRepository postTagRepository,
      UserRepository userRepository,
      PostTagServiceImpl postTagService,
      ImageServiceImpl imageService) {
    this.modelMapper = modelMapper;
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
    this.postTagRepository = postTagRepository;
    this.userRepository = userRepository;
    this.postTagService = postTagService;
    this.imageService = imageService;
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
    return getPostDTOS(posts);
  }

  @Override
  public PostDTO create(PostDTO postDTO) {
    return null;
  }

  public Collection<PostDTO> getByTagId(Long id) {

    Collection<Post> posts = postRepository.findByTagsIn(postTagRepository.findAllByTag(tagRepository.findById(id).get()));
    return getPostDTOS(posts);

  }


  public PostDTO create(String title, String content, MultipartFile file, int[] tags, int authorId) {
    Post post = new Post();
    Image image = imageService.create(file);
    post.setPostImage(image);
    post.setTitle(title);

    if (content != null) {
      post.setContent(content);
    }

    post.setPostAuthor(userRepository.findById((long) authorId).orElseThrow(
        DataNotFoundException::new));
    post.setLink(null);
    post.setTimePosted(LocalDateTime.now());
    postRepository.save(post);

    PostTagForm postTagForm = new PostTagForm();
    postTagForm.setPost(post);
    postTagForm.setTags(tags);
    postTagService.createMany(postTagForm);
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
    if (postDTO.getImageData() == null)
        return postDTO;

    byte[] imageData = post.getPostImage().getImageData();
    if(imageData != null && imageData.length > 0)
      postDTO.setImageData(new String(imageData));
    else
      postDTO.setImageData(null);
    return postDTO;
  }

  private Collection<PostDTO> getPostDTOS(Collection<Post> posts) {
    Collection<PostDTO> postsDTO = modelMapper.map(posts, new TypeToken<Set<PostDTO>>(){}.getType());
    postsDTO.clear();
    for(Post post : posts) {
      postsDTO.add(postToPostDTO(post));
    }
    return postsDTO;
  }
}
