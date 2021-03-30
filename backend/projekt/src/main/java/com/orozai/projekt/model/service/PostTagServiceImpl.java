package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.PostTagDTO;
import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import java.util.Collection;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class PostTagServiceImpl implements IService<PostTagDTO> {
  private final ModelMapper modelMapper;
  private final PostTagRepository postTagRepository;
  private final TagRepository tagRepository;

  public PostTagServiceImpl(ModelMapper modelMapper,
      PostTagRepository postTagRepository,
      TagRepository tagRepository) {
    this.modelMapper = modelMapper;
    this.postTagRepository = postTagRepository;
    this.tagRepository = tagRepository;
  }

  @Override
  public PostTagDTO get(Long id) {
    return modelMapper.map(postTagRepository.findById(id), PostTagDTO.class);
  }

  @Override
  public Collection<PostTagDTO> getAll() {
    return null;
  }

  @Override
  public PostTagDTO create(PostTagDTO postTagDTO) {

    PostTag postTag = modelMapper.map(postTagDTO,PostTag.class);
    postTag.getTag().getTagId();
    postTag.getPost().getContent();

    return postTagDTO;
  }

  @Override
  public PostTagDTO update(PostTagDTO postTagDTO) {
    return null;
  }

  @Override
  public void delete(PostTagDTO postTagDTO) {

  }
}
