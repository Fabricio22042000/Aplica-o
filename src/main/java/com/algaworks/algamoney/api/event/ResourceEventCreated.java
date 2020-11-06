package com.algaworks.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceEventCreated extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Long id;
	
	public ResourceEventCreated(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.id = id;
		this.response = response;
	}

	
	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}
	
	
}
