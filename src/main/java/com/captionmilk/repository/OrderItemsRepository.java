package com.ontheway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.OrderItems;
import com.ontheway.model.DisplayItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
	
	
	@Query("select o.id as id, o.items.name as name,o.items.price as price, o.items.availability as availability,o.items.cookingTime as cookingTime,o.count as count from OrderItems o where o.order.id=(:orderId)")
	List<DisplayItems> getOrderItems(@Param("orderId") Long orderId);

}
