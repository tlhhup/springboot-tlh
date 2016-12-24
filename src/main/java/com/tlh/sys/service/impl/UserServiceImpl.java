package com.tlh.sys.service.impl;

import com.tlh.sys.entity.User;
import com.tlh.sys.mapper.UserMapper;
import com.tlh.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hup on 2016/12/24.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mUserMapper;

    @Override
    public User validateUserInfo(User user) throws Exception {
        return mUserMapper.validateUserInfo(user);
    }
}
