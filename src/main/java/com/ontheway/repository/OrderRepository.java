package com.ontheway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.Orders;
import com.ontheway.model.OrderDetailsDTO;

@Repository
public interface OrderRepository  extends JpaRepository<Orders,Long> {
	
	@Query("select o.id as id, DATE_FORMAT(o.orderOn, '%d %M %Y %h:%i:%s %p') as orderOn, o.user.userId as userId, o.fromCity.name as fromCity, o.toCity.name as toCity, o.orderType.name as orderType,o.user.contact as mobile,o.totalPrice as totalPrice,o.orderStatus.name as orderStatus,o.message as message from Orders o where (o.hotel.user.id=(:userId) or o.user.role.roleName like 'Admin') ")
	List<OrderDetailsDTO> getOrderDetails(@Param("userId") Long userId);


}
