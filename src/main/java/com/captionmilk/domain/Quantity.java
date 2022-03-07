package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user")
	LoginDetails user;

	
	public Quantity()
	{
		
	}
	public Quantity(String name, String description) {
		
		this.name = name;
		this.description = description;
		this.status=true;
	}
	String name;
	String description;
	
	Boolean status;
	
}
