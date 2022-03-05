package com.captionmilk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Products;

@Repository
public interface ProductRepository  extends JpaRepository<Products,Long> {

}
	