package com.algaworks.algamoney.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.ResourceEventCreated;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonResources {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService; 
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<List<Person>> listPerson(){
		return ResponseEntity.ok().body(personRepository.findAll());
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> savePerson(@Valid @RequestBody Person person, HttpServletResponse response) {
		person = personRepository.save(person);
		publisher.publishEvent(new ResourceEventCreated(this, response, person.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Person> findById(@PathVariable Long id){
		Person person = personRepository.findById(id).orElse(null);
		if(person == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void deleteById(@PathVariable Long id) {
		personRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
		return ResponseEntity.ok().body(personService.update(id, person));
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public void updateActiveField(@PathVariable Long id, @RequestBody Boolean isActive) {
		personService.updateActiveField(id, isActive);
	}
	
}