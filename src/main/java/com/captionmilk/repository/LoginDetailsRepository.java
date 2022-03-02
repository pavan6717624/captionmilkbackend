package com.captionmilk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.LoginDetails;

@Repository
public interface LoginDetailsRepository  extends JpaRepository<LoginDetails,Long> {

Optional<LoginDetails> findByEmail(String email);

Optional<LoginDetails> findByContact(Long mobile);
		
}
