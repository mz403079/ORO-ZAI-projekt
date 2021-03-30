package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.specialized.PostFormDTO;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
  public PostDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<PostDTO> getAll() {

    return modelMapper.map(postRepository.findAll(), new TypeToken<Set<PostDTO>>(){}.getType());
  }

  @Override
  public PostDTO create(PostDTO postDTO) {
    return null;
  }

  public Collection<PostDTO> getByTagId(Long id) {
    return modelMapper.map(postRepository.findByTagsIn(postTagRepository.findAllByTag(tagRepository.findById(id).get())),
        new TypeToken<Set<PostDTO>>(){}.getType());
  }

  public PostDTO createTest(PostFormDTO postDTO) {
    Post p = new Post();
    String fileName = StringUtils.cleanPath(postDTO.getImageData().getOriginalFilename());
    if(fileName.contains(".."))
    {
      System.out.println("not a a valid file");
    }
    try {
      p.setImageData(Base64.getEncoder().encodeToString(postDTO.getImageData().getBytes()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    p.setContent(postDTO.getContent());
    p.setTitle(postDTO.getTitle());
    p.setAuthor(null);
    p.setLink(null);
    postRepository.save(p);
    return null;

  }

  @Override
  public PostDTO update(PostDTO postDTO) {
    return null;
  }

  @Override
  public void delete(PostDTO postDTO) {

  }
}
