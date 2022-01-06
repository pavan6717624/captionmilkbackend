package com.ontheway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.Item;
import com.ontheway.model.DisplayItems;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

	@Query("select i.id as id, i.name as name,i.price as price, i.availability as availability,i.cookingTime as cookingTime from Item i where i.hotel.id=(:hotelId)")
	List<DisplayItems> getItems(@Param("hotelId") Long hotelId);

}

