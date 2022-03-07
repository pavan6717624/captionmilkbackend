package com.captionmilk.model;

public interface ProductDTO1 {
	
	Long getId();
	Long getBid();
	Long getCid();
	Long getQid();
	Long getRid();
	
	String getName();
	Long getContact();
	String getType();
	
	String getBrand();
	
	String getCategory();
	
	String getQuantity();
	
	String getRepeatDays();
	
	String getReportDate();

	Long getAmount();

	Long getTodayAmount();
	Long getNetAmount();
	Long getMonthAmount();
	
	
	Boolean getMorning();
	Boolean getEvening();
	Boolean getAfternoon();
	
	String getFromDate();
	
	String getToDate();
	
	Boolean getOutOfHome();
	
	Boolean getServiceAvailed();

}
