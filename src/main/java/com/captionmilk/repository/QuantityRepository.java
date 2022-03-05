package com.captionmilk.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
	
	List<Quantity> findByStatus(@Param("status") Boolean status);
	
	

	@Query(value = "select * from (SELECT ADDDATE(:date1, INTERVAL @i\\:=@i+1 DAY) AS DAY FROM ( SELECT a.a FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS c ) a JOIN (SELECT @i\\:= -1) r1 WHERE  @i < DATEDIFF(:date2, :date1)) t where day between :date3 and :date4",nativeQuery =  true)
	List<String> getDates(@Param("date1") LocalDate date1,  @Param("date2") LocalDate date2, @Param("date3") LocalDate date3,@Param("date4") LocalDate date4);
	

}
