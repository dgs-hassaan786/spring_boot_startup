package io.hassaan.sqlserverapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Product {

	@JsonProperty("Id")
	private long id;
	@JsonProperty("Name")
	private String name;
	
	public Product() {}
	
	public Product(long i, String n) {
		id = i;
		name = n;
	}
	
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId(long i) {
		this.id = i;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
}
