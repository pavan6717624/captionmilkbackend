package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class RepeatDays implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3885707247502994258L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	String description;
	Long repeatDays;
	Boolean status=false;
	
	public RepeatDays()
	{
		
	}
	public RepeatDays(String name, String description, Long repeatDays) {
		
		this.name = name;
		this.description = description;
		this.repeatDays=repeatDays;
		this.status=true;
	}
	
}
