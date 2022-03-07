package com.captionmilk.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
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
public class LoginDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5225024844842578777L;
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long userId;
	String password="";
	String name="";
	Long contact=0l;
	String email="";
	String city="";
	String message="";
	
	
	
	@Column(columnDefinition="datetime")
	 Timestamp  joinDate;
	



	@OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "roleId")
	RolesCM role;
	
	
	Boolean isDisabled;
	Boolean isDeleted;
	


	

	

	

	

}
