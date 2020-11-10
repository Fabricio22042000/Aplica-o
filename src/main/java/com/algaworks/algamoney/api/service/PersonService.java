package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;


@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person update(Long id, Person person) {
		Person personDataBase = findById(id);
		BeanUtils.copyProperties(person, personDataBase, "id");
		return personRepository.save(personDataBase);
	}
	
	public void updateActiveField(Long id, Boolean isActive) {
		Person person = findById(id);
		person.setIsAtivo(isActive);
		personRepository.save(person);
	}
	
	private Person findById(Long id) {
		Person personDataBase = personRepository.findById(id).orElse(null);
		if(personDataBase == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return personDataBase;
	}
	
}
