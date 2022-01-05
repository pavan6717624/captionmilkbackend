package com.ontheway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3206190518914912514L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	String name;

}
