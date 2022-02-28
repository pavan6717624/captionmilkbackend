package com.ontheway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {

	Optional<OrderStatus> findByName(String string);

}
