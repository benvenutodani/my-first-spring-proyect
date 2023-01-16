package org.ada.myfirstspringproject.repository;

import org.ada.myfirstspringproject.entity.AcademicDegree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Integer> {
}
