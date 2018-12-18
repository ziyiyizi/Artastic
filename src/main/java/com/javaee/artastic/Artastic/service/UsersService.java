package com.javaee.artastic.Artastic.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.javaee.artastic.Artastic.domain.ArtworksList;
import com.javaee.artastic.Artastic.domain.Follow;
import com.javaee.artastic.Artastic.domain.Notification;
import com.javaee.artastic.Artastic.domain.Roles;
import com.javaee.artastic.Artastic.domain.UserDetails;
import com.javaee.artastic.Artastic.domain.Users;

public interface UsersService {
	//根据名字查询用户
	public Users findByUserName(String username);
	//查询所有用户
	public List<Users> findAll(); 
	//查询用户的角色列表
	public List<Roles> findRoleList(int userId);
	//查询用户的角色列表
	public List<Roles> findRoleList(Users users);
	//根据id查询用户
	public Users findByUserId(int userId);
	//根据mail查询用户
	public Users findByUserMail(String userMail);
	//根据phone查询用户
	public Users findByUserPhone(String userPhone);
	//根据名字或mail查询用户
	public Users findByUserNameOrUserMail(String userName, String userMail);
	//找到用户id
	public int findUserIdByUserName(String userName);
	//根据作品id查询作家名字
	public String findNameByWorkId(int workId);
	//查询用户名与头像
	public Map<String, Object> findNameAndIconByUserId(int userId);
	//找到用户名
	public String findUserNameByUserId(int userId);
	//找到用户邮箱
	public String findUserMailByUserId(int userId);
	//找到用户手机
	public String findUserPhoneByUserId(int userId);
	//找到用户token
	public String findUserTokenByUserId(int userId);
	//找到用户token有效时间
	public String findTokenTimeByUserId(int userId);
	//查询一定时间内投稿数最多的作家
	public List<Object[]> findByTimeBetweenAndWorkNum(String start, String end);
	//查询用户详细信息，搜索时显示的信息
	public List<UserDetails> findUsers(String userName, Pageable pageable);
	//查询用户详细信息，他人可见的信息
	public UserDetails findUserDetails(String userName, Pageable pageable);
	//查询通知列表
	public List<Notification> findByReceiverName(String receiverName);

	//名字是否存在
	public boolean isUserNameExists(String userName);
	//mail是否存在
	public boolean isUserMailExists(String userMail);
	//phone是否存在
	public boolean isUserPhoneExists(String userPhone);
	//名字或mail是否存在
	public boolean isNameOrMailExists(String userName, String userMail);
	//用户是否激活
	public boolean isUserActivate(String userName);
	//用户是否激活
	public boolean isUserActivate(Users users);
	//是否关注过
	public boolean isFollow(int artistId, int followerId);
	
	//更新用户状态
	public int updateUserStateByUserId(int userId, String userState);
	//更新用户token
	public int updateUserTokenByUserId(int userId, String userToken);
	//更新头像
	public int updateUserIconByUserId(int userId, String userIcon);
	//更新个人资料
	public int updateProfile(String sex, String mail, String pwd, String description, int id);
	//更新通知
	public int updateNotification(String receiverName);

	//更新用户
	public Users save(Users users);
	//更新关注
	public Follow saveFollow(Follow follow);
	//更新通知
	public Notification saveNotification(Notification notification);

	//查询未读通知数量
	public int countNotifyNum(String receiverName);
	//查询关注用户的人数
	public int countFollows(int artistId);
	//发送通知
	public ArtworksList pushNotification(String senderName, String receiverName, String workName, String type, String notiContent, int workId);

	//删除用户
	public void deleteByUserId(int userId);

}
