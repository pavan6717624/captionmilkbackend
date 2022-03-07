package com.captionmilk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Users;
import com.captionmilk.model.UsersDTO;

@Repository
public interface UsersRepository  extends JpaRepository<Users,Long> {

	@Query("select u.name as name,u.contact as contact,u.address as address,u.regularAmount as regularAmount ,u.type as type,u.created as created from Users u where u.type=(:type) and u.userId=(:userId) order by u.createdDate desc")
	List<UsersDTO> findByTypeAndUserId(@Param("type") String type, @Param("userId") Long userId) ;
	
	
	
	Optional<Users> findByContactAndTypeAndUserId(@Param("contact") Long contact,@Param("type") String type, @Param("userId") Long userId) ;
	
}
