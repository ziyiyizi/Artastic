package com.javaee.artastic.Artastic.service.impl;

import java.util.List;

import javax.annotation.Resource;

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
        System.out.println("UsersServiceImpl.findByUsername()");  
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
	
}
