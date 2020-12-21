package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
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

	
	public Launch update(Long id, Launch launch) {
		Launch launchDataBase = findById(id);
		if(!launch.getPerson().equals(launchDataBase.getPerson())) {
			findPersonById(launch.getPerson().getId());
		}
		BeanUtils.copyProperties(launch, launchDataBase, "id");
		
		return launchRepository.save(launchDataBase);
	}
	
	public Person findPersonById(Long id) {
		Person personDataBase = personRepository.findById(id).orElse(null);
		if(personDataBase == null || !personDataBase.getIsAtivo()) {
			throw new PersonNotActiveOrNotExists();
		}
		return personDataBase;
	}
	
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
