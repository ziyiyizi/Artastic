package com.javaee.artastic.Artastic.service;

import java.util.List;

import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.Users;

public interface UsersService {
	public Users findByUserName(String username);
	public List<Users> findAll(); 
	public List<Roles> findRoleList(int userId);
}
