package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostTagDTO;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.repository.PostRepository;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IService<PostDTO> {
  private final ModelMapper modelMapper;
  private final PostRepository postRepository;
  private final TagRepository tagRepository;
  private final PostTagRepository postTagRepository;

  public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository,
      TagRepository tagRepository,
      PostTagRepository postTagRepository) {
    this.modelMapper = modelMapper;
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
    this.postTagRepository = postTagRepository;
  }
  @Override
  public PostDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<PostDTO> getAll() {

    return modelMapper.map(postRepository.findAll(), new TypeToken<Set<PostDTO>>(){}.getType());
  }
  public Collection<PostDTO> getByTagId(Long id) {
    return modelMapper.map(postRepository.findByTagsIn(postTagRepository.findAllByTag(tagRepository.findById(id).get())),
        new TypeToken<Set<PostDTO>>(){}.getType());
  }
  @Override
  public PostDTO create(PostDTO postDTO) {
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
