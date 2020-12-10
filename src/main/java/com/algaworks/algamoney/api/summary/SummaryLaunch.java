package com.algaworks.algamoney.api.summary;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algaworks.algamoney.api.model.KindLaunch;

public class SummaryLaunch {
	
	private Long id;
	private String description;
	private LocalDate dueDate;
	private LocalDate payDate;
	private BigDecimal value;
	private String observation;
	private KindLaunch kindLaunch;
	private String category;
	private String person;
	
	public SummaryLaunch(Long id, String description, LocalDate dueDate, LocalDate payDate, BigDecimal value,
			String observation, KindLaunch kindLaunch, String category, String person) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.payDate = payDate;
		this.value = value;
		this.observation = observation;
		this.kindLaunch = kindLaunch;
		this.category = category;
		this.person = person;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public KindLaunch getKindLaunch() {
		return kindLaunch;
	}
	public void setKindLaunch(KindLaunch kindLaunch) {
		this.kindLaunch = kindLaunch;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	
}
