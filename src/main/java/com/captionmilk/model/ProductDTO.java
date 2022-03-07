package com.captionmilk.model;

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
		
	Boolean morning;
	
	Boolean afternoon;
	Boolean evening;
	
	String fromDate;
	
	String toDate;
	
	Boolean outOfHome;
	
	Boolean serviceAvailed;
	
	Long contact=0l;
	
	String type="";
	
	Boolean created=false;
	
	

	
}
