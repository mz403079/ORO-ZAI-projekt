package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.repository.TagRepository;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements IService<TagDTO> {

  private final ModelMapper modelMapper;
  private final TagRepository tagRepository;

  public TagServiceImpl(ModelMapper modelMapper,
      TagRepository tagRepository) {
    this.modelMapper = modelMapper;
    this.tagRepository = tagRepository;
  }

  @Override
  public TagDTO get(Long id) {
    return modelMapper.map(tagRepository.findById(id), TagDTO.class);
  }

  @Override
  public Collection<TagDTO> getAll() {
    return null;
  }

  @Override
  public TagDTO create(TagDTO tagDTO) {
    return null;
  }

  @Override
  public TagDTO update(TagDTO tagDTO) {
    return null;
  }

  @Override
  public void delete(TagDTO tagDTO) {

  }
}
