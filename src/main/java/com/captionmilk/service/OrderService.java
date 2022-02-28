package com.ontheway.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ontheway.domain.OrderItems;
import com.ontheway.domain.Orders;
import com.ontheway.domain.UserDetails;
import com.ontheway.model.DisplayHotels;
import com.ontheway.model.DisplayItems;
import com.ontheway.model.DisplayItemsDTO;
import com.ontheway.model.OrderDetails;
import com.ontheway.repository.CityRepository;
import com.ontheway.repository.HotelRepository;
import com.ontheway.repository.ItemRepository;
import com.ontheway.repository.OrderItemsRepository;
import com.ontheway.repository.OrderRepository;
import com.ontheway.repository.OrderStatusRepository;
import com.ontheway.repository.OrderTypeRepository;
import com.ontheway.repository.UserDetailsRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	UserDetailsRepository userRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	OrderStatusRepository orderStatusRepository;
	
	@Autowired
	OrderItemsRepository orderItemsRepository;
	
	
	@Transactional
	public Boolean payment(OrderDetails orderDetails)
	{
		
		System.out.println(orderDetails.getOrderType());
		
		Orders order=new Orders();
		order.setFromCity(cityRepository.findById(Long.valueOf(orderDetails.getFromCity())).get());
		order.setToCity(cityRepository.findById(Long.valueOf(orderDetails.getToCity())).get());
		order.setOrderType(orderTypeRepository.findById(Long.valueOf(orderDetails.getOrderType())+1).get());
		org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		UserDetails user=userRepository.findById(Long.valueOf(userDetails.getUsername())).get();
		
		order.setUser(user);
		order.setOrderOn(Timestamp.valueOf(LocalDateTime.now()));
		
		order.setOrderStatus(orderStatusRepository.findByName("Received").get());
		order.setHotel(hotelRepository.findById(orderDetails.getHotelId()).get());
		
		
		order.setTotalPrice(orderDetails.getCart().getTotalPrice());
		
		
		
		orderRepository.save(order);
		
		
		
		List<DisplayItemsDTO> items= orderDetails.getCart().getItems();
		
		for(int i=0;i<items.size();i++)
		{
			OrderItems orderItems = new OrderItems();
			orderItems.setItems(itemRepository.findById(items.get(i).getId()).get());
			orderItems.setCount(items.get(i).getCount());
			orderItems.setOrder(order);
			orderItemsRepository.save(orderItems);
		}
		
		
		return true;
		
		
		
		
		
	}


	public List<DisplayItems> getOrderItems(String orderId) {
		
		return orderItemsRepository.getOrderItems(Long.valueOf(orderId));
	}


	@Transactional
	public Boolean submitOrder(String orderId, String rejectReason, String orderStatus) {
		
		Orders order= orderRepository.findById(Long.valueOf(orderId)).get();
		order.setMessage(rejectReason);
		order.setOrderStatus(orderStatusRepository.findByName(orderStatus).get());
		orderRepository.save(order);
		
		return true;
		
		
	}



	
	
	

}
