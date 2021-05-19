package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.TagCountDTO;
import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.entity.Tag;
import com.orozai.projekt.model.repository.ICount;
import com.orozai.projekt.model.repository.TagRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
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
    return modelMapper
        .map(tagRepository.findById(id).orElseThrow(DataNotFoundException::new), TagDTO.class);
  }

  @Override
  public Collection<TagDTO> getAll() {
    return modelMapper.map(tagRepository.findAll(Sort.by(Sort.Direction.ASC, "tagId")),
        new TypeToken<List<TagDTO>>() {
        }.getType());
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

  public Collection<TagCountDTO> getTop() {
    Collection<ICount> topTags = tagRepository.getTopTags();
    Collection<TagCountDTO> topTagDTOs = modelMapper.<List<TagCountDTO>>map(topTags,
        new TypeToken<List<TagCountDTO>>() {
        }.getType());
    setTags(topTagDTOs);
    return topTagDTOs;
  }

  public void setTags(Collection<TagCountDTO> tops) {
    for (TagCountDTO tag : tops) {
      tag.setTag(this.get(tag.getId()));
    }
  }

  public Set<Tag> assignTagnames(int[] tagIds) {
    Set<Tag> tags = new HashSet<>();
    for (int tagId : tagIds) {
      tags.add(tagRepository.findById((long) tagId).orElseThrow(DataNotFoundException::new));
    }
    return tags;
  }
}
