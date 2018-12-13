package com.javaee.artastic.Artastic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.artastic.Artastic.dao.FollowDao;
import com.javaee.artastic.Artastic.dao.RolesDao;
import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.domain.Users;
import com.javaee.artastic.Artastic.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Resource
	private UsersDao usersDao;
	
	@Resource
	private RolesDao rolesDao;
	
	@Resource
	private FollowDao followDao;
	
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
	public boolean isNameOrMailExists(String userName, String userMail) {
		// TODO Auto-generated method stub
		Users users = usersDao.findByUserNameOrUserMail(userName, userMail);
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

	@Transactional
	@Override
	public int updateUserStateByUserId(int userId, String userState) {
		// TODO Auto-generated method stub
		return usersDao.updateUserStateByUserId(userId, userState);
	}

	@Transactional
	@Override
	public void deleteByUserId(int userId) {
		// TODO Auto-generated method stub
		usersDao.delete((long)userId);
		
	}

	@Transactional
	@Override
	public int updateUserTokenByUserId(int userId, String userToken) {
		// TODO Auto-generated method stub
		return usersDao.updateUserTokenByUserId(userId, userToken);
	}

	@Override
	public List<UserDetails> findUsers(String userName, Pageable pageable) {
		// TODO Auto-generated method stub
		List<UserDetails> userDetailList = new ArrayList<>();
		
		Page<Map<String, Object>> page = usersDao.findUsers(userName, pageable);
		List<Map<String, Object>> detailslist = page.getContent();
		for(Map details : detailslist) {
			UserDetails userDetails = new UserDetails();
			userDetails.setIconURL(String.valueOf(details.get("userIcon")));
			userDetails.setArtistName(String.valueOf(details.get("userName")));
			int userId = Integer.parseInt(String.valueOf(details.get("userId")));
			userDetails.setFrenzy(followDao.countFollows(userId));
			userDetailList.add(userDetails);
		}
		
		return userDetailList;
		
	}

	@Override
	public int findUserIdByUserName(String userName) {
		// TODO Auto-generated method stub
		return usersDao.findUserIdByUserName(userName);
	}

	@Override
	public Follow saveFollow(Follow follow) {
		// TODO Auto-generated method stub
		return followDao.save(follow);
	}

	@Override
	public boolean isFollow(int artistId, int followerId) {
		// TODO Auto-generated method stub
		if(followDao.findByArtistIdAndFollowerId(artistId, followerId) == null) {
			return false;
		} else {
			return true;
		}
		
	}
	
	
	
	
}
