package com.captionmilk.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class OTP  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7394192677669688555L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	Long mobile;
	String otp;
	
	@Column(columnDefinition="datetime")
	 Timestamp  sentOn;
	
	
	@Column(columnDefinition="datetime")
	 Timestamp  validTill;
	
	Boolean validated;
	
	String email;
	
	String name;
	
	
	

}
