package com.ontheway.model;

import java.util.List;

public class Cart {
	
	List<DisplayItemsDTO> items;
	public List<DisplayItemsDTO> getItems() {
		return items;
	}
	public void setItems(List<DisplayItemsDTO> items) {
		this.items = items;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	Double totalPrice;

}
