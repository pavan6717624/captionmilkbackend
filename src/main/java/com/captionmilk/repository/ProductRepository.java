package com.captionmilk.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.LoginDetails;
import com.captionmilk.domain.Products;
import com.captionmilk.domain.Users;
import com.captionmilk.model.ProductDTO1;

@Repository
public interface ProductRepository  extends JpaRepository<Products,Long> {
	
	@Query("select p.id as id, "
			+ "p.category.id as cid, p.category.name as category, "
			+ "p.brand.id as bid, p.brand.name as brand,"
			+ "p.quantity.id as qid, p.quantity.name as quantity, "
			+ "p.repeat.id as rid, p.repeat.name as repeatDays, "
			+ "p.amount as amount, p.morning as morning, p.afternoon as afternoon, p.evening as evening,"
			+ "p.fromDate as fromDate, p.toDate as toDate,p.serviceAvailed as serviceAvailed, p.outOfHome as outOfHome,"
			+ "(select sum(p1.amount) as todayAmount from Products p1 where  p1.fromDate <= (:date) and floor(((DATEDIFF((:date),(p1.fromDate)))) % p1.repeat.repeatDays) = 0 and ((p1.loginUser.contact=:#{#loginUser.contact} and p1.user.contact=:#{#user.contact}) or (p1.loginUser.contact=:#{#user.contact} and p1.user.contact=:#{#loginUser.contact}))) as todayAmount,"
			+ "(select sum(p1.amount*(floor(((DATEDIFF((:date),(p1.fromDate))+p1.repeat.repeatDays)) / p1.repeat.repeatDays))) as netAmount from Products p1 where  p1.fromDate <= (:date)  and ((p1.loginUser.contact=:#{#loginUser.contact} and p1.user.contact=:#{#user.contact}) or (p1.loginUser.contact=:#{#user.contact} and p1.user.contact=:#{#loginUser.contact}))) as netAmount,"
		//	+ "(select sum(p1.amount*((DATEDIFF((:date),case when :monthStart > p1.fromDate then (:monthStart) else p1.fromDate end))+1)) as monthAmount from Products p1 where  p1.fromDate <= (:date) and floor(((DATEDIFF((:date),(p1.fromDate)))+1) / p1.repeat.repeatDays) = 0 and ((p1.loginUser.contact=:#{#loginUser.contact} and p1.user.contact=:#{#user.contact}) or (p1.loginUser.contact=:#{#user.contact} and p1.user.contact=:#{#loginUser.contact})))  as monthAmount "
			+ "(select sum(p1.amount*(floor(((DATEDIFF((:date),(case when :monthStart > p1.fromDate then (:monthStart) else p1.fromDate end))+p1.repeat.repeatDays)) / p1.repeat.repeatDays))) as monthAmount from Products p1 where  p1.fromDate <= (:date)  and ((p1.loginUser.contact=:#{#loginUser.contact} and p1.user.contact=:#{#user.contact}) or (p1.loginUser.contact=:#{#user.contact} and p1.user.contact=:#{#loginUser.contact})))  as monthAmount "
			
			+ "  from Products p where p.fromDate <= (:date) and floor(((DATEDIFF((:date),(p.fromDate)))) % p.repeat.repeatDays) = 0 and ((p.loginUser.contact=:#{#loginUser.contact} and p.user.contact=:#{#user.contact}) or (p.loginUser.contact=:#{#user.contact} and p.user.contact=:#{#loginUser.contact}))")
	List<ProductDTO1> getProducts(@Param("date") Timestamp date,@Param("monthStart") Timestamp monthStart,@Param("loginUser") LoginDetails loginUser, @Param("user") Users user);
	
	
	@Query(value = "select p.id as id, c.day as reportDate, "
			+ " (select name from users u where u.id=p.user) as name, "
			+ " (select contact from users u where u.id=p.user) as contact, "
			+ " (select type from users u where u.id=p.user) as type, "
			+ "(select name from categorycm where id = cid) as category, "
			+ "(select name from brand where id = bid)  as brand,"
			+ "(select name from quantity where id = qid)  as quantity, "
			+ "(select name from repeat_days where id = rid)  as repeatDays, "
			+ "p.amount as amount, p.morning as morning, p.afternoon as afternoon, p.evening as evening,"
			+ "p.from_date as fromDate, p.to_date as toDate,p.service_availed as serviceAvailed, p.out_of_home as outOfHome from products p, "
			+ "(SELECT ADDDATE(:fromDate, INTERVAL @i\\:=@i+1 DAY) AS DAY FROM "
			+ "( SELECT a.a FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL "
			+ "SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL "
			+ "SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) "
			+ "AS a CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 "
			+ "UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL "
			+ "SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b CROSS JOIN "
			+ "(SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL "
			+ "SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT"
			+ " 8 UNION ALL SELECT 9) AS c ) a JOIN (SELECT @i\\:= -1) r1 WHERE  @i < DATEDIFF(:toDate, :fromDate)) c"
			+ " where p.from_date <= c.day and (p.login_user=:#{#loginUser.userId} or p.user in (select id from users u where u.contact=:#{#loginUser.contact}))"
			+ " and  (DATEDIFF(c.day , p.from_date)) % (select repeat_days from repeat_days where id=rid) = 0 "
			+ " order by c.day, p.from_date asc", nativeQuery=true)
	List<ProductDTO1> getProductReport(@Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate,@Param("loginUser") LoginDetails loginUser);
}
	