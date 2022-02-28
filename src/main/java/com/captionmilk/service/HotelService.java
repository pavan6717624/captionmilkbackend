package com.ontheway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ontheway.domain.Hotel;
import com.ontheway.model.DisplayHotels;
import com.ontheway.model.OrderDetailsDTO;
import com.ontheway.repository.DistanceRepository;
import com.ontheway.repository.HotelRepository;
import com.ontheway.repository.OrderRepository;

@Service
public class HotelService {
	
	@Autowired
	DistanceRepository distanceRepository;

	
	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	OrderRepository orderRepository;

	
	public DisplayHotels getHotelDetails() {
		
		org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		return hotelRepository.getHotelDetails(Long.valueOf(userDetails.getUsername()));
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

	public List<OrderDetailsDTO> getOrderDetails() {
		
		org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("Restaurant")))
			
		return orderRepository.getOrderDetails(Long.valueOf(userDetails.getUsername()));
		else
			return orderRepository.getOrderDetails(-1L);
			

		
	}
	
	public DisplayHotels hotelStatus(String hotelStatus) {
		
		
		org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Hotel hotel=hotelRepository.getHotel(Long.valueOf(userDetails.getUsername())).get();
		
		hotel.setOnlineStatus(Boolean.parseBoolean(hotelStatus));
		
		hotelRepository.save(hotel);
		
		System.out.println("status :: "+hotel.getOnlineStatus());
		
		return hotelRepository.getHotelDetails(Long.valueOf(userDetails.getUsername()));
		
		
	}

	

}
