package org.ada.myfirstspringproject.controller;

import org.ada.myfirstspringproject.dto.AcademicDegreeDTO;
import org.ada.myfirstspringproject.service.AcademicDegreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons/{personId}/academic-degrees")
public class AcademicDegreeController {

    private final AcademicDegreeService academicDegreeService;

    public AcademicDegreeController(AcademicDegreeService academicDegreeService) {
        this.academicDegreeService = academicDegreeService;
    }

    @PostMapping
    public ResponseEntity create(@PathVariable String personId,
                                 @RequestBody AcademicDegreeDTO academicDegreeDTO) {
        academicDegreeService.create(academicDegreeDTO, personId);

        return new ResponseEntity(academicDegreeDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{academicDegreeId}")
    public ResponseEntity retrieveById(@PathVariable String personId,
                                       @PathVariable Integer academicDegreeId){
        AcademicDegreeDTO academicDegreeDTO = academicDegreeService.retrieveById(personId, academicDegreeId);

        return new ResponseEntity(academicDegreeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{academicDegreeId}")
    public ResponseEntity delete(@PathVariable Integer academicDegreeId) {
        academicDegreeService.delete(academicDegreeId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
