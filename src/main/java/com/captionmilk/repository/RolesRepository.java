package com.captionmilk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.captionmilk.domain.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
	
	Roles findByRoleName(String roleName);
}
