package com.javaee.artastic.Artastic.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.javaee.artastic.Artastic.domain.Error;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Notification;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.domain.Users;

public interface UsersService {
	public Users findByUserName(String username);
	public List<Users> findAll(); 
	public List<Roles> findRoleList(int userId);
	public List<Roles> findRoleList(Users users);
	public int findUserIdByUserName(String userName);
	public String findNameByWorkId(int workId);
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
	public List<UserDetails> findUsers(String userName, Pageable pageable);
	public UserDetails findUserDetails(String userName, Pageable pageable);
	public boolean isFollow(int artistId, int followerId);
	public Follow saveFollow(Follow follow);
	public List<Notification> findByReceiverName(String receiverName);
	public Notification saveNotification(Notification notification);
	public int updateNotification(String receiverName);
	public int countNotifyNum(String receiverName);
	public Error pushNotification(String senderName, String receiverName, String workName, String type, String notiContent, int workId);
}
