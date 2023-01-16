package org.ada.myfirstspringproject.service;

import org.ada.myfirstspringproject.repository.PersonRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
 @ExtendWith(MockitoExtension.class)
class PersonServiceTest {
  @Mock
  private PersonRepository personRepository;

  @Mock
  private AcademicDegreeService academicDegreeService;

  @InjectMocks
  private PersonService personService;

}