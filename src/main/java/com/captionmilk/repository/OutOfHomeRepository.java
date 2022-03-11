package com.captionmilk.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.captionmilk.domain.LoginDetails;
import com.captionmilk.domain.OutOfHome;
import com.captionmilk.domain.Products;
import com.captionmilk.domain.Users;

public interface OutOfHomeRepository  extends JpaRepository<OutOfHome,Long> {
	Optional<OutOfHome> findByLoginUserAndUserAndOnDate(@Param("loginUser") LoginDetails loginUser, @Param("user") Users user, @Param("onDate") Timestamp onDate);
}
