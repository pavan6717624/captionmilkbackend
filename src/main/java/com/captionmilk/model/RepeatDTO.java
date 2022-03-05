package com.captionmilk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data

public class RepeatDTO {
	/**
	 * 
	 */

	Long id;
	String name;
	String description;
	Long addDays;
	
	
	public RepeatDTO()
	{
		
	}
	public RepeatDTO(String name, String description, Long addDays) {
		
		this.name = name;
		this.description = description;
		this.addDays=addDays;
	}
	
}
