package com.captionmilk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data

public class CategoryDTO {
	
	
	Long id;
	
	
	public CategoryDTO()
	{
		
	}
	public CategoryDTO(String name, String description,Long repeatDays) {
		
		this.name = name;
		this.description = description;
		this.repeatDays = 0l;
	}
	String name;
	String description;
	Long repeatDays;
	
	

}
