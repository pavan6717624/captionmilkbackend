package com.captionmilk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Quantity;
import com.captionmilk.domain.Repeat;

@Repository
public interface RepeatRepository extends JpaRepository<Repeat,Long> {
	Optional<Repeat> findByName(@Param("name") String name);

}
