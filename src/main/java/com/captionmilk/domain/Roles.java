package com.captionmilk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Roles implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 645594300782428396L;
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String roleName="";
	String displayName="";
	
	
	
	
	
	
	
}
