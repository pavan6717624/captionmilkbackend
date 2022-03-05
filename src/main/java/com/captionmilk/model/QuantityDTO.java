package com.captionmilk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data

public class QuantityDTO {
	/**
	 * 
	 */
	
	Long id;
	
	String name;
	String description;
	
	public QuantityDTO()
	{
		
	}
	public QuantityDTO(String name, String description) {
		
		this.name = name;
		this.description = description;
	}
	
}
