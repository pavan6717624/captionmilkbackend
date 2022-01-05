package com.ontheway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
	
	

}
