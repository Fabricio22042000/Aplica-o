package com.algaworks.algamoney.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String publicSpace;
	private String number;
	private String complement;
	private String neighborhood;
	private String cep;
	private String city;
	private String state;
	
	
	
	public String getPublicSpace() {
		return publicSpace;
	}
	public void setPublicSpace(String publicSpace) {
		this.publicSpace = publicSpace;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
