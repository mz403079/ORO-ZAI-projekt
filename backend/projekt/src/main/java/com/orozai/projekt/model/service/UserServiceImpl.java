package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.UserCountDTO;
import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.entity.ERole;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.Role;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.ImageRepository;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.RoleRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements IService<UserDTO> {

  private final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ImageServiceImpl imageService;
  private final PostRepository postRepository;
  private final ImageRepository imageRepository;

  @Autowired
  private final PasswordEncoder encoder;

  public UserServiceImpl(ModelMapper modelMapper,
      UserRepository userRepository,
      RoleRepository roleRepository,
      ImageServiceImpl imageService,
      PostRepository postRepository,
      ImageRepository imageRepository, PasswordEncoder encoder) {
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.imageService = imageService;
    this.postRepository = postRepository;
    this.imageRepository = imageRepository;
    this.encoder = encoder;
  }

  @Override
  public UserDTO get(Long id) {
    User user = userRepository.findById(id).orElseThrow(DataNotFoundException::new);
    return userToUserDTO(user);
  }

  @Override
  public Collection<UserDTO> getAll() {
    return null;
  }

  @Override
  public UserDTO create(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    user.setPassword(encoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);
    user.setProfileImage(imageRepository.findByImageId(2L));
    userRepository.save(user);
    return null;
  }

  public void updateProfilePic(Long userId, MultipartFile file) {
    User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
    Image image = new Image();
    try {
      image.setImageData(Base64.getEncoder().encode(file.getBytes()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    user.setProfileImage(image);
    userRepository.save(user);

  }

  @Override
  public UserDTO update(UserDTO userDTO) {
    return null;
  }

  @Transactional
  public UserDTO update(UserDTO userDTO, PostDTO postDTO) {
    User user = userRepository.findById(userDTO.getUserId())
        .orElseThrow(DataNotFoundException::new);
    Post post = postRepository.findById(postDTO.getPostId())
        .orElseThrow(DataNotFoundException::new);
    if (user.getLikedPosts().contains(post)) {
      user.removeLike(post);
    } else {
      user.addLike(post);
    }
    return userDTO;
  }

  public UserDTO userToUserDTO(User user) {
    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    userDTO.setProfileImage(imageService.imageToImageDTO(user.getProfileImage()));
    return userDTO;
  }

  public void setUsers(Collection<UserCountDTO> tops) {
    for (UserCountDTO user : tops) {
      user.setUser(this.get(user.getId()));
    }
  }

  @Override
  public void delete(UserDTO userDTO) {

  }

  public UserDTO delete(Long id) {
    System.out.println("removing");
    User user = userRepository.findById(id).orElseThrow(
        DataNotFoundException::new);
    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    Set<Post> posts = user.getLikedPosts();
    for(Post post : posts) {
      post.removeLike(user);
    }
//    Set<Post> posts2 = user.getPosts();
//    for(Post post : posts2) {
//     System.out.println(post.getTitle());
//    }
//
//    for(Post post : posts2) {
//      post.removeAuthor(user);
//    }
//    for(Post post : posts2) {
//      user.removePost(post);
//    }
    userRepository.delete(user);
    System.out.println("removed");
    return userDTO;
  }
}
