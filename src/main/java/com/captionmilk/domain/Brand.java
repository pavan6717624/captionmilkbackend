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
public class Brand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5806192929664459391L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	
	public Brand()
	{
		
	}
	public Brand(String name, String description, LoginDetails user) {
		
		this.name = name;
		this.description = description;
		this.status=true;
		this.user=user;
	}
	String name;
	String description;
	
	Boolean status;
	@OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user")
	LoginDetails user;
}
