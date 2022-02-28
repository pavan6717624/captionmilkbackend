package com.ontheway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderItems implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4784787160190798041L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "itemId")
	Item items;
	
	Integer count;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "orderId")
	Orders order;
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
}
