package org.ada.myfirstspringproject.repository;

import org.ada.myfirstspringproject.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

}
