package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Quantity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7312237954795290758L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name;
	String description;
	
	public Quantity()
	{
		
	}
	public Quantity(String name, String description) {
		
		this.name = name;
		this.description = description;
	}
	
}
