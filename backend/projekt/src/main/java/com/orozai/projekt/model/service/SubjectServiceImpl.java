package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.SubjectDTO;
import com.orozai.projekt.model.entity.Subject;
import com.orozai.projekt.model.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class SubjectServiceImpl implements IService<SubjectDTO> {

  private final ModelMapper modelMapper;
  private final SubjectRepository subjectRepository;

  public SubjectServiceImpl(ModelMapper modelMapper, SubjectRepository subjectRepository) {

    this.modelMapper = modelMapper;
    this.subjectRepository = subjectRepository;
  }

  @Override
  public SubjectDTO get(Long id) {
    return modelMapper.map(subjectRepository.findAll(), new TypeToken<Set<SubjectDTO>>(){}.getType());
  }

  @Override
  public Collection<SubjectDTO> getAll() {
    return modelMapper.map(subjectRepository.findAll(), new TypeToken<Set<SubjectDTO>>(){}.getType());
  }

  @Override
  public SubjectDTO create(SubjectDTO subjectDTO) {
    subjectRepository.save(modelMapper.map(subjectDTO, Subject.class));
    return subjectDTO;
  }

  @Override
  public SubjectDTO update(SubjectDTO subjectDTO) {
    return subjectDTO;
  }

  @Override
  public void delete(SubjectDTO subjectDTO) {
    subjectRepository.deleteById(subjectDTO.getSubjectId());
  }
}
