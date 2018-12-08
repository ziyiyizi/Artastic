package com.javaee.artastic.Artastic.dao;

import com.javaee.artastic.Artastic.domain.Users;

import java.util.List;

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
	
	@Query("select userName from Users where userId = :userId")
	public String findUserNameByUserId(@Param("userId")int userId);
	
	@Modifying
	@Query("update Users set userState = :userState where userId = :userId")
	public int updateUserStateByUserId(@Param("userId")int userId, @Param("userState")String userState);
	
	@Modifying
	@Query("update Users set userToken = :userToken where userId = :userId")
	public int updateUserTokenByUserId(@Param("userId")int userId, @Param("userToken")String userToken);
}
