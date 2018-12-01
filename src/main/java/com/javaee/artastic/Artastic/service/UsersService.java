package com.javaee.artastic.Artastic.service;

import com.javaee.artastic.Artastic.domain.Users;

public interface UsersService {
	public Users findByUserName(String username);
}
