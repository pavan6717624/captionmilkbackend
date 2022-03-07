package com.captionmilk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.LoginDetails;
import com.captionmilk.domain.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
	
	List<Quantity> findByStatus(@Param("status") Boolean status);
	
	List<Quantity> findByStatusAndUserOrderByIdDesc(@Param("status") Boolean status, LoginDetails user);

	
}
