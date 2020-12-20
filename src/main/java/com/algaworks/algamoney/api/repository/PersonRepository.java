package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.person.PersonRepositoryQuery;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuery{

}
