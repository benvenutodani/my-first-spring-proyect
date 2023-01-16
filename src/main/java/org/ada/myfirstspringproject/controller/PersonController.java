package org.ada.myfirstspringproject.controller;

import org.ada.myfirstspringproject.dto.PersonDTO;
import org.ada.myfirstspringproject.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity create(@RequestHeader(value = "client-id") String clientId,
                                 @RequestBody PersonDTO personDTO) {
        PersonDTO createdPersonDTO = personService.create(personDTO);

        return new ResponseEntity(personDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve() {

        return new ResponseEntity(personService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity retrieveById(@PathVariable String personId) {
        PersonDTO personDTO = personService.retrieveById(personId);

        return new ResponseEntity(personDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity delete(@PathVariable String personId) {
        personService.delete(personId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{personId}")
    public ResponseEntity replace(@PathVariable String personId,
                                  @RequestBody PersonDTO personDTO) {
        personService.replace(personId, personDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{personId}")
    public ResponseEntity modify(@PathVariable String personId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        personService.modify(personId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
