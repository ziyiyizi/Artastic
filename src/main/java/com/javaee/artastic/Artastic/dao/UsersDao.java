package com.javaee.artastic.Artastic.dao;

import com.javaee.artastic.Artastic.domain.Users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UsersDao extends JpaRepository<Users, Long> {
	public Users findByUserName(String username);
	public Users findByUserId(int userId);
	public Users findByUserMail(String userMail);
	public Users findByUserPhone(String userPhone);
	public Users findByUserNameOrUserMail(String userName, String userMail);
	
	@Query("select u.userName from Users u, Artworks ru where ru.artistId=u.userId and ru.artworkId=:workId")
	public String findNameByWorkId(@Param("workId")int workId);
	
	@Query("select userId from Users where userName = :userName")
	public int findUserIdByUserName(@Param("userName")String userName);
	
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
	
	@Query("select userState from Users where userName = :userName")
	public String findUserStateByUserName(@Param("userName")String userName);
	
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
	
	@Modifying
	@Query("update Users set userSex = :sex,userMail = :mail,userPassword = :pwd,userDescription = :description where userId = :id")
	public int updateUserByUserId(@Param("sex")String sex, @Param("mail")String mail, @Param("pwd")String pwd, @Param("description")String description, @Param("id")int id);
	
	@Query("select new map(u.userId as userId, u.userName as userName, u.userIcon as userIcon) from Users u where u.userName like %:userName%")
	public Page<Map<String, Object>> findUsers(@Param("userName")String userName, Pageable pageable);
	
	@Query("select new map(u.userId as userId, u.userName as userName, u.userIcon as userIcon) from Users u where u.userName = :userName")
	public Map<String, Object> findUsersEX(@Param("userName")String userName);
	
	@Query("select new map(u.userId as userId, u.userName as userName, u.userIcon as userIcon) from Users u, Follow ru where ru.artistId = :artistId and ru.followerId = u.userId order by ru.followtime")
	public Page<Map<String, Object>> findFollower(@Param("artistId")int artistId, Pageable pageable);
	
	@Query("select new map(u.userId as userId, u.userName as userName, u.userIcon as userIcon) from Users u, Follow ru where ru.followerId = :artistId and ru.artistId = u.userId order by ru.followtime")
	public Page<Map<String, Object>> findFollowing(@Param("artistId")int artistId, Pageable pageable);
	
	@Query("select count(*) from Artworks where artistId=:userId")
	public int countWorks(@Param("userId")int userId);
	
	@Query("select new map(registertime as time, userDescription as description) from Users where userId=:userId")
	public Map<String, Object> findTimeAndDescription(@Param("userId")int userId);
	
	//一定时间内投稿数最多
	@Query(value="select u.User_Name,ru.Artist_ID,count(*) from artworks ru, users u where ru.Artist_ID=u.User_ID and ru.Uploadtime between ?1 and ?2 group by Artist_ID order by count(*) desc limit 10", nativeQuery=true)
	public List<Object[]> findByTimeBetweenAndWorkNum(String start, String end);
	
}
