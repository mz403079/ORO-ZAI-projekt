package com.orozai.projekt.model.repository;
import com.orozai.projekt.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

  Optional<Subject> findBySubjectName(String name);
}
