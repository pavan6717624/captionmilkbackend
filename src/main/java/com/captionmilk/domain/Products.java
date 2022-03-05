package com.captionmilk.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

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
	Category category;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "qid")
	Quantity quantity;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "rid")
	RepeatDays repeat;
		
	Long amount;	
	
	Long daySchedule;
	
	@Column(columnDefinition="datetime")
	 Timestamp  fromDate;
	
	@Column(columnDefinition="datetime")
	 Timestamp  toDate;
	
	Boolean serviceAvailed;
	
	Boolean outOfHome;
	
}
