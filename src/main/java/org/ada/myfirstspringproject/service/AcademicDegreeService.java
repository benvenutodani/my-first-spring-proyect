package org.ada.myfirstspringproject.service;

import org.ada.myfirstspringproject.dto.AcademicDegreeDTO;
import org.ada.myfirstspringproject.entity.AcademicDegree;
import org.ada.myfirstspringproject.entity.Person;
import org.ada.myfirstspringproject.exceptions.ResourceNotFoundException;
import org.ada.myfirstspringproject.repository.AcademicDegreeRepository;
import org.ada.myfirstspringproject.repository.PersonRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicDegreeService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final AcademicDegreeRepository academicDegreeRepository;
    private final PersonRepository personRepository;

    public AcademicDegreeService(AcademicDegreeRepository academicDegreeRepository, PersonRepository personRepository) {
        this.academicDegreeRepository = academicDegreeRepository;
        this.personRepository = personRepository;
    }

    public void create(AcademicDegreeDTO academicDegreeDTO, String personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isEmpty()) {
            throw new ResourceNotFoundException("La persona a la que se está asociando no existe.");
        }

        AcademicDegree academicDegree = mapToEntity(academicDegreeDTO, person.get());
        academicDegree = academicDegreeRepository.save(academicDegree);
        academicDegreeDTO.setId(academicDegree.getId());
    }

    public void create(List<AcademicDegreeDTO> academicDegreeDTOS, Person person) {
        List<AcademicDegree> academicDegrees = academicDegreeDTOS.stream()
                .map(academicDegreeDTO -> mapToEntity(academicDegreeDTO, person))
                .collect(Collectors.toList());
        academicDegreeRepository.saveAll(academicDegrees);
    }

    public AcademicDegreeDTO retrieveById(String personId, Integer academicDegreeId) {
        if (!personRepository.existsById(personId)){
            throw new ResourceNotFoundException("La persona no existe.");
        }
        Optional<AcademicDegree> academicDegree = academicDegreeRepository.findById(academicDegreeId);
        if (!academicDegree.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return mapToDTO(academicDegree.get());
    }

    private AcademicDegree mapToEntity(AcademicDegreeDTO academicDegreeDTO, Person person) {
        AcademicDegree academicDegree = new AcademicDegree(academicDegreeDTO.getTitle(),
                academicDegreeDTO.getInstitution(), LocalDate.parse(academicDegreeDTO.getDate(), DATE_TIME_FORMATTER),
                person);

        return academicDegree;
    }

    public List<AcademicDegreeDTO> mapToDTOS(List<AcademicDegree> academicDegrees) {

        return academicDegrees.stream()
                .map(academicDegree -> mapToDTO(academicDegree))
                .collect(Collectors.toList());
    }

    private AcademicDegreeDTO mapToDTO(AcademicDegree academicDegree) {
        AcademicDegreeDTO academicDegreeDTO = new AcademicDegreeDTO(academicDegree.getTitle(),
                academicDegree.getInstitution(), academicDegree.getDate().toString());
        academicDegreeDTO.setId(academicDegree.getId());

        return academicDegreeDTO;
    }

    public void delete(Integer academicDegreeId) {
        try {
            academicDegreeRepository.deleteById(academicDegreeId);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException();
        }
    }
}
