package com.algaworks.algamoney.api.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceEventCreated>{

	@Override
	public void onApplicationEvent(ResourceEventCreated resourceEventCreated) {
		HttpServletResponse response = resourceEventCreated.getResponse();
		Long id = resourceEventCreated.getId();
		
		adicionarHeaderLocation(response, id);
	}
	
	 private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
					buildAndExpand(id).toUri();
		 response.setHeader("Location", uri.toASCIIString());
	 }

}
