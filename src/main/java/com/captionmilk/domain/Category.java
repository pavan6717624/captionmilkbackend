package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2731114610410207773L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	
	public Category()
	{
		
	}
	public Category(String name, String description) {
		
		this.name = name;
		this.description = description;
		this.status=true;
	}
	String name;
	String description;
	
	Boolean status;
	
	

}
