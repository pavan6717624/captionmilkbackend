package com.ontheway.model;

public interface OrderDetailsDTO {
	
	public Long getId() ;
	public String getOrderOn() ;
	public Long getUserId() ;
	public String getFromCity() ;
	public String getToCity() ;
	public String getOrderType() ;
	public String getMobile();
	public Double getTotalPrice();
	public String getOrderStatus();
	public String getMessage();

}
