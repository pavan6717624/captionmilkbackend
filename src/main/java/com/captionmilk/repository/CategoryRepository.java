package com.captionmilk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.CategoryCM;
import com.captionmilk.domain.LoginDetails;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryCM,Long> {
	List<CategoryCM> findByStatus(@Param("status") Boolean status);
	
	List<CategoryCM> findByStatusAndUserOrderByIdDesc(@Param("status") Boolean status, LoginDetails user);
	

}
