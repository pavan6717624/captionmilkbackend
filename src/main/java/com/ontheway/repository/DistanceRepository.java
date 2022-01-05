package com.ontheway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ontheway.domain.Distance;
import com.ontheway.model.DisplayHotels;

@Repository
public interface DistanceRepository extends JpaRepository<Distance,Long> {

	@Query("select d.hotel.id as id,d.hotel.name as name, d.hotel.address as address, d.distance as distance from Distance d where d.fromCity.id=(:fromCity) and d.toCity.id=(:toCity) and d.distance >=(:distance1) and d.distance<(:distance2) and d.hotel.onlineStatus=true and (select count(*) from Item i where i.hotel.id=d.hotel.id and (((:type1)=true and i.takeAway=true) or ((:type2)=true and i.dineIn=true) or ((:type3)=true and i.driveIn=true))) > 0")
	List<DisplayHotels> getHotels(@Param("fromCity") Long fromCity, @Param("toCity") Long toCity,@Param("distance1") Double distance1,@Param("distance2") Double distance2,@Param("type1") Boolean type1,@Param("type2") Boolean type2,@Param("type3") Boolean type3);
	
}
