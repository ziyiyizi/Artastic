package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.UserRole;

@Component
public interface UserRoleDao extends JpaRepository<UserRole, Long>{

	public List<UserRole> findByUserid(int userId);
}
