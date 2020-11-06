package com.algaworks.algamoney.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.ResourceEventCreated;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonResources {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<Person>> listPerson(){
		return ResponseEntity.ok().body(personRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Person> savePerson(@Valid @RequestBody Person person, HttpServletResponse response) {
		person = personRepository.save(person);
		publisher.publishEvent(new ResourceEventCreated(this, response, person.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id){
		Person person = personRepository.findById(id).orElse(null);
		if(person == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(person);
	}
	
}
