package com.captionmilk.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductDTO  {
	
	Long id;
	Long bid;
	Long cid;
	Long qid;
	Long rid;
	
	String brand;
	
	String category;
	
	String quantity;
	
	String repeatDays;

	Long amount;
		
	Long daySchedule;
	
	String fromDate;
	
	String toDate;
	
	Boolean serviceAvailed;
	
	Boolean outOfHome;
	
}
