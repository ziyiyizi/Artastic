package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Roles;

@Component
public interface RolesDao extends JpaRepository<Roles, Long>{

	@Query("select u from Roles u, UserRole ru where u.roleId = ru.roleid and ru.userid = :userid")
	List<Roles> findRolesByUser(@Param("userid")int userid);
}
