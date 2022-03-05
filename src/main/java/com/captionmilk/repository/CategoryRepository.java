package com.captionmilk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Category;
import com.captionmilk.domain.Repeat;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	Optional<Category> findByName(@Param("name") String name);
}
