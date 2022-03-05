package com.captionmilk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	List<Category> findByStatus(@Param("status") Boolean status);
	

}
