package com.javaee.artastic.Artastic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.artastic.Artastic.dao.RolesDao;
import com.javaee.artastic.Artastic.dao.UserRoleDao;
import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.UserRole;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Resource
	private UsersDao usersDao;
	
	@Resource
	private RolesDao rolesDao;
	
	@Transactional(readOnly=true)  
    @Override  
    public Users findByUserName(String username) {  
        return usersDao.findByUserName(username);  
    }  
	
	@Override
	public List<Users> findAll(){
		return usersDao.findAll();
	}
	
	@Override
	public List<Roles> findRoleList(int userId){
		return rolesDao.findRolesByUser(userId);
		
	}

	@Override
	public List<Roles> findRoleList(Users users) {
		// TODO Auto-generated method stub
		int userid = users.getUserId();
		return rolesDao.findRolesByUser(userid);
	}

	@Override
	public String findUserNameByUserId(int userId) {
		// TODO Auto-generated method stub
		return usersDao.findUserNameByUserId(userId);
	}

	@Override
	public Users save(Users users) {
		// TODO Auto-generated method stub
		return usersDao.save(users);
	}

	@Override
	public String findUserMailByUserId(int userId) {
		// TODO Auto-generated method stub
		return usersDao.findUserMailByUserId(userId);
	}

	@Override
	public String findUserPhoneByUserId(int userId) {
		// TODO Auto-generated method stub
		return usersDao.findUserPhoneByUserId(userId);
	}

	@Override
	public boolean isUserNameExists(String userName) {
		// TODO Auto-generated method stub
		Users users = usersDao.findByUserName(userName);
		if(users == null) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public boolean isUserMailExists(String userMail) {
		// TODO Auto-generated method stub
		Users users = usersDao.findByUserMail(userMail);
		if(users == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isUserPhoneExists(String userPhone) {
		// TODO Auto-generated method stub
		Users users = usersDao.findByUserPhone(userPhone);
		if(users == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isUserActivate(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String findUserTokenByUserId(int userId) {
		// TODO Auto-generated method stub
		return usersDao.findUserTokenByUserId(userId);
	}

	@Override
	public String findTokenTimeByUserId(int userId) {
		// TODO Auto-generated method stub
		return usersDao.findTokenTimeByUserId(userId).toString();
	}

	@Override
	public int updateUserStateByUserId(int userId, String userState) {
		// TODO Auto-generated method stub
		return usersDao.updateUserStateByUserId(userId, userState);
	}

	@Override
	public void deleteByUserId(int userId) {
		// TODO Auto-generated method stub
		usersDao.delete((long)userId);
		
	}

	@Override
	public int updateUserTokenByUserId(int userId, String userToken) {
		// TODO Auto-generated method stub
		return usersDao.updateUserTokenByUserId(userId, userToken);
	}
	
	
	
	
}
