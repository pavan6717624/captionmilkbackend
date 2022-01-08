package com.ontheway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.Hotel;
import com.ontheway.model.DisplayHotels;
import com.ontheway.model.OrderDetailsDTO;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
	
	@Query("select h.id as id,h.name as name, h.address as address,h.onlineStatus as onlineStatus from Hotel h where h.user.userId=(:userId)")
	DisplayHotels getHotelDetails(@Param("userId") Long userId);
	
	
	@Query("select h from Hotel h where h.user.userId=(:userId)")
	Optional<Hotel> getHotel(@Param("userId") Long userId);

	
}
