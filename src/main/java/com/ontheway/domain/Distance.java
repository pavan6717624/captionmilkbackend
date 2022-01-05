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
public class Distance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9033524870138998533L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "hotelId")
	Hotel hotel;
	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "fromCityId")
	City fromCity;
	
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

	@OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "toCityId")
	City toCity;
	
	double distance;
}
