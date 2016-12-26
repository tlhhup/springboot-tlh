package com.tlh.sys.mapper;

import com.tlh.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

//	@Insert("INSERT INTO sys_users(userName,password,realName) VALUES(#{username},#{password},#{realName})")
	int saveUser(User user) throws Exception;

//	@Delete("delete from sys_users where id=#{id}")
	int deleteUser(User user) throws Exception;
	
	int updateUser(User user) throws Exception;
	
	List<User> findUserInfos(User user) throws Exception;

//	@Select("select * from sys_users where username=#{userName} and password=#{password}")
	User validateUserInfo(User user) throws Exception;
	
}
