package com.captionmilk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
	
	Optional<Quantity> findByName(@Param("name") String name);

}
