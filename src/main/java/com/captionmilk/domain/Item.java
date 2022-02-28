package com.ontheway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8499308868304318198L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Boolean getTakeAway() {
		return takeAway;
	}

	public void setTakeAway(Boolean takeAway) {
		this.takeAway = takeAway;
	}

	public Boolean getDriveIn() {
		return driveIn;
	}

	public void setDriveIn(Boolean driveIn) {
		this.driveIn = driveIn;
	}

	public Boolean getDineIn() {
		return dineIn;
	}

	public void setDineIn(Boolean dineIn) {
		this.dineIn = dineIn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public Boolean getAvailablity() {
		return availability;
	}

	public void setAvailablity(Boolean availability) {
		this.availability = availability;
	}

	String name;
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "hotelId")
	Hotel hotel;
	
	Boolean takeAway;
	Boolean driveIn;
	Boolean dineIn;
	
	double price;
	
	int cookingTime;
	
	Boolean availability;

}
