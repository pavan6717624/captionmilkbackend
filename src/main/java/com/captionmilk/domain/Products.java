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
public class Products implements Serializable {

	private static final long serialVersionUID = 5249708653299963538L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "bid")
	Brand brand;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "cid")
	CategoryCM category;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "qid")
	Quantity quantity;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "rid")
	RepeatDays repeat;
		
	Long amount;	
	
	Boolean morning;
	
	Boolean evening;
	
	Boolean afternoon;
	
	@Column(columnDefinition="datetime")
	 Timestamp  fromDate;
	
	@Column(columnDefinition="datetime")
	 Timestamp  toDate;
	
	Boolean outOfHome;
	
	Boolean serviceAvailed;
	
	@OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "loginUser")
	LoginDetails loginUser;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user")
	Users user;

	
}
