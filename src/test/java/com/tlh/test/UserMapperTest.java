package com.tlh.test;

import com.tlh.TlhApplication;
import com.tlh.sys.entity.User;
import com.tlh.sys.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=TlhApplication.class)
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void save() throws Exception{
		User user=new User();
		user.setUserName("admin");
		user.setRealName("管理员");
		user.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
		userMapper.saveUser(user);
	}

	@Test
	public void validate() throws Exception{
		User validateUserInfo = userMapper.validateUserInfo("admin");
		Assert.assertNotNull(validateUserInfo);
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
