package com.ontheway.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheway.domain.City;
import com.ontheway.model.DisplayHotels;
import com.ontheway.model.DisplayItems;
import com.ontheway.repository.CityRepository;
import com.ontheway.repository.DistanceRepository;
import com.ontheway.repository.ItemRepository;

@Service
public class CustomerService {
	
	@Autowired
	CityRepository cityRepository;
	@Autowired
	DistanceRepository distanceRepository;
	@Autowired
	ItemRepository itemRepository;
	
	public List<City> getCities()
	{
		return cityRepository.findAll();
	}
	
	
	@Transactional
	public List<DisplayItems> getItems(String hotelId) {
		return itemRepository.getItems(Long.valueOf(hotelId));
		}

}
