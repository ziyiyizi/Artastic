package com.javaee.artastic.Artastic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.javaee.artastic.Artastic.entity.User;

@Mapper
public interface UserMapper {
	@Select("select * from user where id = #{id}")
    public User selectUserById(int id);

    @Select("select * from user where userName = #{userName}")
    public List<User> selectUserByName(String userName);

    @Insert("insert into user(userName,userAge,userAddress) values (#{userName},#{userAge},#{userAddress})")
    public void addUser(User user);

    @Update("update user set userName=#{userName},userAge=#{userAge},userAddress=#{userAddress} where id=#{id}")
    public void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    public void deleteUser(int id);

}
