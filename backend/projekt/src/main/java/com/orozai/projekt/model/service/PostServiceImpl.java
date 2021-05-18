package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostLikeDTO;
import com.orozai.projekt.model.dto.basic.UserCountDTO;
import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.ICount;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.TagRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements IService<PostDTO> {

  private final ModelMapper modelMapper;
  private final PostRepository postRepository;
  private final TagRepository tagRepository;
  private final UserRepository userRepository;
  private final ImageServiceImpl imageService;

  public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository,
      TagRepository tagRepository,
      UserRepository userRepository,
      ImageServiceImpl imageService) {
    this.modelMapper = modelMapper;
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
    this.userRepository = userRepository;
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
    List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "timePosted"));
    return getPostDTOS(posts);
  }

  public Collection<PostDTO> getByQuery(String query) {
    Collection<Post> posts = postRepository
        .findByTitleContainingOrContentContainingOrPostAuthorUsernameContaining(query, query,
            query);
    return getPostDTOS(posts);
  }

  @Override
  public PostDTO create(PostDTO postDTO) {
    return null;
  }

  public Collection<PostDTO> getByTagId(Long id) {
    Collection<Post> posts = postRepository.findAllByTagsTagId(id);
    return getPostDTOS(posts);
  }

  public Collection<PostDTO> getLikedPosts(long userId) {
    User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
    return getPostDTOS(user.getLikedPosts());
  }

  public Collection<PostDTO> getByUserId(long userId) {
    Collection<Post> posts = postRepository.findAllByPostAuthorUserId(userId);
    return getPostDTOS(posts);
  }

  public Collection<PostDTO> getProfilePosts(long userId) {
    User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
    List<Post> posts = (List<Post>) postRepository.findAllByPostAuthorUserId(userId);
    posts.addAll(user.getLikedPosts());
    posts.sort(Comparator.comparing(Post::getTimePosted).reversed());
    return getPostDTOS(posts);
  }

  public Collection<PostDTO> getTopLikedPosts() {
    List<Post> posts = postRepository.findAll();
    posts.sort(Comparator.comparing(Post::getNumOfLikes).reversed());
    return getPostDTOS(posts);
  }

  public Collection<UserCountDTO> getTopUserIds() {
    Collection<ICount> topUserIds = postRepository.getTopUserIds();
    return modelMapper.<List<UserCountDTO>>map(topUserIds, new TypeToken<List<UserCountDTO>>() {
    }.getType());
  }


  public PostDTO create(String title, String content, MultipartFile file, Set<Tag> tags,
      int authorId) {
    Post post = new Post();
    Image image = imageService.create(file);
    post.setPostImage(image);
    post.setTitle(title);
    post.setTags(tags);
    if (content != null) {
      post.setContent(content);
    }

    post.setPostAuthor(userRepository.findById((long) authorId).orElseThrow(
        DataNotFoundException::new));

    post.setTimePosted(LocalDateTime.now());
    postRepository.save(post);

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
    PostDTO postDTO = modelMapper.map(post, PostDTO.class);
    postDTO.setPostImage(imageService.imageToImageDTO(post.getPostImage()));
    postDTO.getAuthor()
        .setProfileImage(imageService.imageToImageDTO(post.getPostAuthor().getProfileImage()));
    postDTO.setNumOfComments(post.getComments().size());
    postDTO.setScore(post.getLikes().size());
    return postDTO;
  }

  private Collection<PostDTO> getPostDTOS(Collection<Post> posts) {
    List<PostDTO> postsDTO = new ArrayList<>();
    for (Post post : posts) {
      postsDTO.add(postToPostDTO(post));
    }
    return postsDTO;
  }

  public Collection<PostDTO> getByUserUsername(String username) {
    Collection<Post> posts = postRepository.findAllByPostAuthorUsername(username);
    return getPostDTOS(posts);
  }
}
