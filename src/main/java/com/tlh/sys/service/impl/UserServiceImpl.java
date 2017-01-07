package com.tlh.sys.service.impl;

import com.tlh.sys.entity.User;
import com.tlh.sys.mapper.UserMapper;
import com.tlh.sys.service.UserService;
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
}
