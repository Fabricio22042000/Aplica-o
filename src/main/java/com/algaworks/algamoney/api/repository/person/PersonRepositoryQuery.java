package com.algaworks.algamoney.api.repository.person;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.filter.PersonFilter;

public interface PersonRepositoryQuery {
	
	public Page<Person> filter(PersonFilter personFilter,Pageable pageable);
	
}
