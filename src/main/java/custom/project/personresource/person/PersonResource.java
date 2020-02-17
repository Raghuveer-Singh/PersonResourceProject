package custom.project.personresource.person;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import custom.project.personresource.exception.PersonAlreadyExistsException;
import custom.project.personresource.exception.PersonNotFoundException;

@RestController
public class PersonResource {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/persons")
	public List<Person> retrieveAll(){
		return personRepository.findAll();
	}
	
	@GetMapping("/persons/{id}")
	public Person retrieveOne(@PathVariable Integer id) {
		Optional<Person> person = personRepository.findById(id);
		if(!person.isPresent()) {
			throw new PersonNotFoundException("Person with id-"+id+" does not exist.");
		}
		else return person.get();
	}
	
	@PostMapping("/persons")
	public ResponseEntity<Object> createPerson(@RequestBody Person person) {
		Person savedPerson;
		if(personRepository.existsById(person.getId())) {
			throw new PersonAlreadyExistsException("Person with id-"+person.getId()+" already exists.");
		}else {
			savedPerson = personRepository.save(person);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPerson.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
	
	@PutMapping("/persons/{id}")
	public void update(@PathVariable Integer id, @RequestBody Person updatedPerson) {
		Optional<Person> person = personRepository.findById(id);
		if(!person.isPresent()) {
			// Create new person if it does not exist
			updatedPerson.setId(id);
			personRepository.save(updatedPerson);
		}
		else {
			// Update person if it exists
			person.get().setFirst_name(updatedPerson.getFirst_name());
			person.get().setLast_name(updatedPerson.getLast_name());
			person.get().setAge(updatedPerson.getAge());
			person.get().setFavourite_colour(updatedPerson.getFavourite_colour());
			person.get().setHobby(updatedPerson.getHobby());
			personRepository.save(person.get());
		}
	}
	
	@DeleteMapping("/persons/{id}")
	public void removePerson(@PathVariable Integer id) {
		Optional<Person> person = personRepository.findById(id);
		if(!person.isPresent()) {
			throw new PersonNotFoundException("Person with id-"+id+" does not exist.");
		}
		else {
			personRepository.delete(person.get());
		}
	}

}
