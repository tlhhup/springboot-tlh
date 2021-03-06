package com.tlh.sys.mapper;

import com.tlh.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper {

	int saveUser(User user) throws Exception;

	int deleteUser(User user) throws Exception;
	
	int updateUser(User user) throws Exception;
	
	List<User> findUserInfos(User user) throws Exception;

	User validateUserInfo(String userName) throws Exception;

    Collection<String> findRoles(String userName);

	Collection<String> findPermissions(String userName);
}
