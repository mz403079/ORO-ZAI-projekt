package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostLikeDTO;
import com.orozai.projekt.model.dto.basic.TagCountDTO;
import com.orozai.projekt.model.dto.basic.UserCountDTO;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostLike;
import com.orozai.projekt.model.entity.PostTagForm;
import com.orozai.projekt.model.repository.ICount;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
  private final PostTagRepository postTagRepository;
  private final UserRepository userRepository;
  private final PostTagServiceImpl postTagService;
  private final ImageServiceImpl imageService;
  private final PostLikeServiceImpl postLikeService;

  public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository,
      TagRepository tagRepository,
      PostTagRepository postTagRepository,
      UserRepository userRepository,
      PostTagServiceImpl postTagService,
      ImageServiceImpl imageService,
      PostLikeServiceImpl postLikeService) {
    this.modelMapper = modelMapper;
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
    this.postTagRepository = postTagRepository;
    this.userRepository = userRepository;
    this.postTagService = postTagService;
    this.imageService = imageService;
    this.postLikeService = postLikeService;
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
    List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC,"timePosted"));
    return getPostDTOS(posts);
  }

  public Collection<PostDTO> getByQuery(String query) {
    Collection<Post> posts = postRepository.findByTitleContainingOrContentContainingOrPostAuthorUsernameContaining(query,query,query);
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

  public Collection<PostDTO> getLikedPosts(long userId) {
    Collection<PostLikeDTO> postLikes = postLikeService.getByUserId(userId);
    Collection<PostDTO> posts = new HashSet<>();
    for(PostLikeDTO postLikeDTO : postLikes) {
      posts.add(get(postLikeDTO.getUserId()));
    }
    return posts;
  }

  public Collection<PostDTO> getTopLikedPosts() {
    Collection<Post> posts = postRepository.findByOrderByScoreDesc();
    return getPostDTOS(posts);
  }
  public Collection<UserCountDTO> getTopUserIds() {
   Collection<ICount> topUserIds =  postRepository.getTopUserIds();
    return modelMapper.<List<UserCountDTO>>map(topUserIds, new TypeToken<List<UserCountDTO>>(){}.getType());
  }
  public Collection<PostDTO> getByUserId(long userId) {
    Collection<Post> posts = postRepository.findAllByPostAuthorUserId(userId);
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

  public PostDTO update(long postId, int vote) {
    Post post = postRepository.findById(postId).orElseThrow(DataNotFoundException::new);
    System.out.println("ESSSA like"+vote);
    post.setScore(post.getScore()+vote);
    postRepository.save(post);
    return null;
  }
  @Override
  public void delete(PostDTO postDTO) {

  }

  public PostDTO postToPostDTO(Post post) {
    PostDTO postDTO = modelMapper.map(post,PostDTO.class);
    postDTO.setPostImage(imageService.imageToImageDTO(post.getPostImage()));
    postDTO.getAuthor().setProfileImage(imageService.imageToImageDTO(post.getPostAuthor().getProfileImage()));
    postDTO.setNumOfComments(post.getComments().size());
    return postDTO;
  }

  private Collection<PostDTO> getPostDTOS(Collection<Post> posts) {
    List<PostDTO> postsDTO = new ArrayList<>();
    for(Post post : posts) {
      postsDTO.add(postToPostDTO(post));
    }
    return postsDTO;
  }

  public Collection<PostDTO> getByUserUsername(String username) {
    Collection<Post> posts = postRepository.findAllByPostAuthorUsername(username);
    return getPostDTOS(posts);
  }
}
