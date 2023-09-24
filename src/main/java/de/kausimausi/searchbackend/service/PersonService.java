package de.kausimausi.searchbackend.service;

import de.kausimausi.searchbackend.ResponseDto;
import de.kausimausi.searchbackend.exception.BadRequestException;
import de.kausimausi.searchbackend.exception.ResourceNotFoundException;
import de.kausimausi.searchbackend.persistence.Person;
import de.kausimausi.searchbackend.persistence.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService {
    private PersonRepository personRepository;
    public Person save(Person person){
        return personRepository.save(person);
    }
    public ResponseDto<List<Person>> findByName(Pageable pageable, String first, String last){
        Page<Person> people = personRepository.findByAttributes(pageable, first, last);
        int pages = people.getTotalPages();
        return new ResponseDto<>(people.stream().toList(), pages);
    }
    public Person updatePerson(Long id, Person person){
        person.setId(id);
        return personRepository.save(person);
    }
    public Person getPersonById(Long id){
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()) throw new ResourceNotFoundException("person not found");
        return personOptional.get();
    }
}
