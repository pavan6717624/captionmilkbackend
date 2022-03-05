package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Brand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5806192929664459391L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name;
	String description;
	
	public Brand()
	{
		
	}
	public Brand(String name, String description) {
		
		this.name = name;
		this.description = description;
	}

}
