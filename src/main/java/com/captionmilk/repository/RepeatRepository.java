package com.captionmilk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.RepeatDays;

@Repository
public interface RepeatRepository extends JpaRepository<RepeatDays,Long> {
	List<RepeatDays> findByStatus(@Param("status") Boolean status);
}
