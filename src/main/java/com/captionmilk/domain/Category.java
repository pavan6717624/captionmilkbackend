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
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2731114610410207773L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user")
	LoginDetails user;
	
	
	public Category()
	{
		
	}
	public Category(String name, String description,LoginDetails user) {
		
		this.name = name;
		this.description = description;
		this.status=true;
		this.user=user;
	}
	String name;
	String description;
	
	Boolean status;
	
	

}
