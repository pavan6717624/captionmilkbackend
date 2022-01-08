package com.ontheway.domain;

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

import com.ontheway.model.OrderDetails;


@Entity
public class Orders implements Serializable{

	/**
	 * 
	 */
	
	public Orders()
	{
		
	}
	

	private static final long serialVersionUID = -4830525240846568885L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public UserDetails getUser() {
		return user;
	}


	public void setUser(UserDetails user) {
		this.user = user;
	}


	public Timestamp getOrderOn() {
		return orderOn;
	}


	public void setOrderOn(Timestamp orderOn) {
		this.orderOn = orderOn;
	}


	public City getFromCity() {
		return fromCity;
	}


	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}


	public City getToCity() {
		return toCity;
	}


	public void setToCity(City toCity) {
		this.toCity = toCity;
	}


	public OrderType getOrderType() {
		return orderType;
	}


	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "userId")
	UserDetails user;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "hotelId")
	Hotel hotel;
	
	@Column(columnDefinition="datetime")
	 Timestamp  orderOn;
	
	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "fromCityId")
	City fromCity;
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "toCityId")
	City toCity;
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "orderTypeId")
	OrderType orderType;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "orderStatusId")
	OrderStatus orderStatus;
	
	Double totalPrice;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	String message;
	

	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
