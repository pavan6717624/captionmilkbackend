package com.captionmilk.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Users implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -6000504184319250625L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name="";
	Long contact=0l;
	String address="";
	Long regularAmount=0l;
	String type="";
	
	Long userId=0l;
	Boolean created=true;
	
	@Column(columnDefinition="datetime")
	 Timestamp  createdDate;
	
	public Users()
	{
		
	}
	
	public Users(String name, Long contact, String address, Long regularAmount,String type, Long userId, Boolean created) {
		
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.regularAmount=regularAmount;
		this.type=type;
		this.userId=userId;
		this.created=created;
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
	}
	

}
