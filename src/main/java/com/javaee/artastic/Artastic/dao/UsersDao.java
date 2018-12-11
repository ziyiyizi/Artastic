package com.javaee.artastic.Artastic.dao;

import com.javaee.artastic.Artastic.domain.Users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UsersDao extends JpaRepository<Users, Long> {
	public Users findByUserName(String username);
	public Users findByUserId(String userId);
	public Users findByUserMail(String userMail);
	public Users findByUserPhone(String userPhone);
	public Users findByUserNameOrUserMail(String userName, String userMail);
	
	@Query("select userName from Users where userId = :userId")
	public String findUserNameByUserId(@Param("userId")int userId);
	
	@Query("select userPassword from Users where userId = :userId")
	public String findUserPasswordByUserId(@Param("userId")int userId);
	
	@Query("select userMail from Users where userId = :userId")
	public String findUserMailByUserId(@Param("userId")int userId);
	
	@Query("select userPhone from Users where userId = :userId")
	public String findUserPhoneByUserId(@Param("userId")int userId);
	
	@Query("select userIcon from Users where userId = :userId")
	public String findUserIconByUserId(@Param("userId")int userId);
	
	@Query("select userState from Users where userId = :userId")
	public String findUserStateByUserId(@Param("userId")int userId);
	
	@Query("select userToken from Users where userId = :userId")
	public String findUserTokenByUserId(@Param("userId")int userId);
	
	@Query("select tokenTime from Users where userId = :userId")
	public Timestamp findTokenTimeByUserId(@Param("userId")int userId);
	
	@Query("select new map(userName as name, userIcon as icon) from Users where userId = :userId")
	public Map<String, Object> findNameAndIconByUserId(@Param("userId")int userId);
	
	@Modifying
	@Query("update Users set userName = :userName where userId = :userId")
	public int updateUserNameByUserId(@Param("userId")int userId, @Param("userName")String userName);
	
	@Modifying
	@Query("update Users set userMail = :userMail where userId = :userId")
	public int updateUserMailByUserId(@Param("userId")int userId, @Param("userMail")String userMail);
	
	@Modifying
	@Query("update Users set userPassword = :userPassword where userId = :userId")
	public int updatePasswordByUserId(@Param("userId")int userId, @Param("userPassword")String userPassword);
	
	@Modifying
	@Query("update Users set userPhone = :userPhone where userId = :userId")
	public int updateUserPhoneByUserId(@Param("userId")int userId, @Param("userPhone")String userPhone);
	
	@Modifying
	@Query("update Users set userIcon = :userIcon where userId = :userId")
	public int updateUserIconByUserId(@Param("userId")int userId, @Param("userIcon")String userIcon);
	
	@Modifying
	@Query("update Users set userState = :userState where userId = :userId")
	public int updateUserStateByUserId(@Param("userId")int userId, @Param("userState")String userState);
	
	@Modifying
	@Query("update Users set userToken = :userToken where userId = :userId")
	public int updateUserTokenByUserId(@Param("userId")int userId, @Param("userToken")String userToken);
}
