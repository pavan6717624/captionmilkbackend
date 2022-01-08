package com.ontheway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.OrderType;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType,Long> {

	Optional<OrderType> findByName(String orderType);

}
