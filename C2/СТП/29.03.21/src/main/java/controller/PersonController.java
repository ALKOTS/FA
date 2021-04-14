package controller;

import entity.Person;
import repository.PersonRepository;
import task.PersonTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonTask personTask;

    @Autowired
    public PersonController(PersonTask personTask) {
        this.personTask = personTask;
    }

    @PostMapping(value = "/persons")
    public ResponseEntity<?> create(@RequestBody Person person) {
        Person newPerson = personTask.create(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> read() {
        final List<Person> persons = personTask.readAll();
        return persons != null && !persons.isEmpty() ? new ResponseEntity<>(persons, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Person> read(@PathVariable(name = "id") int id) {
        final Person person = personTask.read(id);
        return person != null ? new ResponseEntity<>(person, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<Person> put(@PathVariable(name = "id") int id, @RequestBody Person newPerson) {
        if (personTask.update(newPerson, id)) {
            Person newPersonWithId = personTask.read(id);
            return new ResponseEntity<>(newPersonWithId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final Person person = personTask.read(id);
        if (person != null) {
            personTask.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
