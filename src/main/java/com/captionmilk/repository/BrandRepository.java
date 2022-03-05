package com.captionmilk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Brand;
import com.captionmilk.domain.Repeat;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
	
	Optional<Brand> findByName(@Param("name") String name);

}
