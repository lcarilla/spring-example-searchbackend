package de.kausimausi.searchbackend;

import de.kausimausi.searchbackend.exception.BadRequestException;
import de.kausimausi.searchbackend.persistence.Person;
import de.kausimausi.searchbackend.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
// TODO: @Entities should not be exposed here, use DTOs
public class Controller {
    private PersonService personService;
    @PostMapping(path = "/")
    public Person post(@RequestBody Person person){
        return personService.save(person);
    }
    @GetMapping(path = "/")
    public ResponseDto<List<Person>> get(@RequestParam(required = false) String first,
                            @RequestParam(required = false) String last,
                            @RequestParam int page,
                            @RequestParam int size){
        if(size <= 0 || page < 0) throw new BadRequestException("page size must be greater than 0");
        return personService.findByName(PageRequest.of(page, size), first, last);
    }
    @PutMapping(path = "/{id}")
    public Person post(@RequestBody Person person,
                       @PathVariable Long id){
                return personService.updatePerson(id, person);
    }
    @GetMapping(path = "/{id}")
    public Person getOne(@PathVariable Long id){
        return personService.getPersonById(id);
    }
}
