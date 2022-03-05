package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Repeat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3885707247502994258L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	String description;
	Long addDays;
	
	
	public Repeat()
	{
		
	}
	public Repeat(String name, String description, Long addDays) {
		
		this.name = name;
		this.description = description;
		this.addDays=addDays;
	}
	
}
