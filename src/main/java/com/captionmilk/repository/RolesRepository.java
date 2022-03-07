package com.captionmilk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.RolesCM;

@Repository
public interface RolesRepository extends JpaRepository<RolesCM,Long> {
	
	RolesCM findByRoleName(String roleName);
}
