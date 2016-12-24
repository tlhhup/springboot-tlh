package com.tlh.test;

import com.tlh.TlhApplication;
import com.tlh.sys.entity.User;
import com.tlh.sys.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=TlhApplication.class)
@Transactional//每次操作之后回滚事务
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void save() throws Exception{
		User user=new User();
		user.setUsername("张三");
		user.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
		userMapper.saveUser(user);
	}
	
	@Test
	public void findUserInfo() throws Exception{
		List<User> users = userMapper.findUserInfos(null);
		if(users!=null&&!users.isEmpty()){
			for(User user:users){
				System.out.println(user);
			}
		}
	}
	
}
