package com.captionmilk.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.captionmilk.domain.OTP;

public interface OTPRepository  extends JpaRepository<OTP,Long> {
	
	@Query("select o from OTP o where o.mobile=(:mobile) and o.validTill >= (:currentTime) and o.validated!=true")
	
	Optional<OTP> getOTP(@Param("mobile") Long mobile, @Param("currentTime") Timestamp currentTime);

	@Query("select o from OTP o where o.mobile=(:mobile) and o.validTill >= (:currentTime) and o.otp=(:otp)  and o.validated!=true")
	Optional<OTP> checkOTP(@Param("mobile") Long mobile, @Param("currentTime") Timestamp currentTime,  @Param("otp") String otp);

}
