package com.mak.todo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@ApiModel(description = "all validation description")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	protected User() {
	}

	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
