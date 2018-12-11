package com.javaee.artastic.Artastic.service;

import java.util.List;

import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.Users;

public interface UsersService {
	public Users findByUserName(String username);
	public List<Users> findAll(); 
	public List<Roles> findRoleList(int userId);
	public List<Roles> findRoleList(Users users);
	public String findUserNameByUserId(int userId);
	public String findUserMailByUserId(int userId);
	public String findUserPhoneByUserId(int userId);
	public String findUserTokenByUserId(int userId);
	public String findTokenTimeByUserId(int userId);
	public Users save(Users users);
	public boolean isUserNameExists(String userName);
	public boolean isUserMailExists(String userMail);
	public boolean isUserPhoneExists(String userPhone);
	public boolean isNameOrMailExists(String userName, String userMail);
	public boolean isUserActivate(String userName);
	public int updateUserStateByUserId(int userId, String userState);
	public void deleteByUserId(int userId);
	public int updateUserTokenByUserId(int userId, String userToken);
}
