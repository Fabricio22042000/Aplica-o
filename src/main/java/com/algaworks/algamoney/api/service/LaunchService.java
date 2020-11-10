package com.algaworks.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Launch;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.LaunchRepository;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.exceptions.PersonNotActiveOrNotExists;

@Service
public class LaunchService {
	
	@Autowired
	private LaunchRepository launchRepository;
	
	@Autowired
	private PersonRepository personRepository;

	
	public Launch findById(Long id) {
		Launch launchDataBase = launchRepository.findById(id).orElse(null);
		if(launchDataBase == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return launchDataBase;
	}
	
	public Launch save(Launch launch) {
		Person person = personRepository.findById(launch.getPerson().getId()).orElse(null);
		if(person == null || !person.getIsAtivo()) {
			throw new PersonNotActiveOrNotExists();
		}
		return launchRepository.save(launch);
	}
}
