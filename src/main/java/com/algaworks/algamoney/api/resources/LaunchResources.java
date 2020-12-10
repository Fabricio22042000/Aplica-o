package com.algaworks.algamoney.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.ResourceEventCreated;
import com.algaworks.algamoney.api.model.Launch;
import com.algaworks.algamoney.api.repository.LaunchRepository;
import com.algaworks.algamoney.api.repository.filter.LaunchFilter;
import com.algaworks.algamoney.api.service.LaunchService;
import com.algaworks.algamoney.api.service.exceptions.PersonNotActiveOrNotExists;
import com.algaworks.algamoney.api.summary.SummaryLaunch;
import com.algaworks.algamoney.api.model.Error;

@RestController
@RequestMapping("/launch")
public class LaunchResources {
	
	@Autowired
	private LaunchRepository launchRepository;
	
	@Autowired
	private LaunchService launchService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Launch> search(LaunchFilter launchFilter, Pageable pageable){
		return launchRepository.filter(launchFilter, pageable);
	}
	
	@GetMapping(params = "summary")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<SummaryLaunch> summary(LaunchFilter launchFilter, Pageable pageable){
		return launchRepository.summary(launchFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Launch> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(launchService.findById(id));
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Launch> save(@Valid @RequestBody Launch launch, HttpServletResponse response){
		launch = launchService.save(launch);
		publisher.publishEvent(new ResourceEventCreated(this, response, launch.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(launch);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public void delete(@PathVariable Long id){
		launchRepository.deleteById(id);
	}
	
	//Handle person not active or person not exists
	@ExceptionHandler({PersonNotActiveOrNotExists.class})
	public ResponseEntity<Object> handlePersonNotActiveOrNotExists(PersonNotActiveOrNotExists ex){
		String userMessage = messageSource.getMessage("person.not-Active-or-Exists", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		List<Error> errorList = new ArrayList<>();
		errorList.add(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errorList);
	}
	
}
