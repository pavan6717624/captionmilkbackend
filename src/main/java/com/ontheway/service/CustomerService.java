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
	
	public List<DisplayHotels> getHotels(String fromCity, String toCity, String orderType, String distance)
	{
		Double fromDistance = 0d, toDistance=1000d;
		if(distance != null)
			{
			if(distance.equals("0"))
			
			{
				fromDistance=0d;
				toDistance=50d;
			}
			else if(distance.equals("1"))
			{
				fromDistance=51d;
				toDistance=100d;
			}
			else if(distance.equals("2"))
			{
				fromDistance=101d;
				toDistance=200d;
			}
			else if(distance.equals("3"))
			{
				fromDistance=201d;
				toDistance=300d;
			}
			else if(distance.equals("4"))
			{
				fromDistance=301d;
				toDistance=1000d;
			}
			
			
	}
		Boolean typeOrder[]={false,false,false};
		typeOrder[Integer.parseInt(orderType)]=true;
		return distanceRepository.getHotels(Long.valueOf(fromCity), Long.valueOf(toCity), fromDistance,toDistance,typeOrder[0],typeOrder[1],typeOrder[2] );
	}
	
	@Transactional
	public List<DisplayItems> getItems(String hotelId) {
		return itemRepository.getItems(Long.valueOf(hotelId));
		}

}
