package com.captionmilk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Brand;
import com.captionmilk.domain.CategoryCM;
import com.captionmilk.domain.LoginDetails;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
	List<Brand> findByStatus(@Param("status") Boolean status);
	
	List<Brand> findByStatusAndUserOrderByIdDesc(@Param("status") Boolean status, LoginDetails user);
}
