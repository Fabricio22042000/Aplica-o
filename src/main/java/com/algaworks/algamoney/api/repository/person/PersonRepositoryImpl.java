package com.algaworks.algamoney.api.repository.person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.filter.PersonFilter;

public class PersonRepositoryImpl implements PersonRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Person> filter(PersonFilter personFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate[] predicates = createPredicates(personFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Person> query = manager.createQuery(criteria);
		addPaginationsRestrictions(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalElements(personFilter));
	}

	private Long totalElements(PersonFilter personFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate[] predicates = createPredicates(personFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private void addPaginationsRestrictions(TypedQuery<Person> query, Pageable pageable) {
		int totalElementsInPage = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int getNextElements = totalElementsInPage * pageNumber;
		
		query.setMaxResults(totalElementsInPage);
		query.setFirstResult(getNextElements);
	}

	private Predicate[] createPredicates(PersonFilter personFilter, CriteriaBuilder builder, Root<Person> root) {
		List<Predicate> listPredicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(personFilter.getName())) {
			listPredicates.add(builder.like(root.get("name"), '%' + personFilter.getName() + '%'));
		}
		return listPredicates.toArray(new Predicate[listPredicates.size()]);
	}

}
