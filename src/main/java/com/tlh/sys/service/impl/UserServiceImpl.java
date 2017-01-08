package com.tlh.sys.service.impl;

import com.tlh.sys.config.TlhProperties;
import com.tlh.sys.entity.User;
import com.tlh.sys.mapper.UserMapper;
import com.tlh.sys.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by hup on 2016/12/24.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mUserMapper;

    @Autowired
    private TlhProperties mTlhProperties;

    @Override
    public User validateUserInfo(String userName)  {
        try {
            return mUserMapper.validateUserInfo(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<String> findRoles(String userName) {
        return mUserMapper.findRoles(userName);
    }

    @Override
    public Collection<String> findPermissions(String userName) {
        return mUserMapper.findPermissions(userName);
    }

    @Override
    public boolean saveUserInfo(User user) throws Exception {
        prepareUser(user);
        return this.mUserMapper.saveUser(user)>0;
    }

    @Override
    public boolean deleteUserInfo(User user) throws Exception {
        return this.mUserMapper.deleteUser(user)>0;
    }

    @Override
    public boolean updateUserInfo(User user) throws Exception {
        prepareUser(user);
        return this.mUserMapper.updateUser(user)>0;
    }

    private void prepareUser(User user){
        //生成凭证的盐,加强密码
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        salt=user.getUserName()+salt;
        user.setSalt(salt);
        //加密
        SimpleHash hash=new SimpleHash(Md5Hash.ALGORITHM_NAME,user.getPassword(),salt,mTlhProperties.getHashIterations());
        user.setPassword(hash.toHex());
    }

}
