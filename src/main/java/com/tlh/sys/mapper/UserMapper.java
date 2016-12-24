package com.tlh.sys.mapper;

import java.util.List;

import com.tlh.sys.entity.User;

public interface UserMapper {

	int saveUser(User user) throws Exception;
	
	int deleteUser(User user) throws Exception;
	
	int updateUser(User user) throws Exception;
	
	List<User> findUserInfos(User user) throws Exception;
	
}
