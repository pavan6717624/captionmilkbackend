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
public class OutOfHome implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1994365338464549758L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(columnDefinition="datetime")
	 Timestamp  onDate;
	Boolean outOfHome;
	
	@OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "loginUser")
	LoginDetails loginUser;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user")
	Users user;
	
	
}
