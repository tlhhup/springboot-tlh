package com.tlh.sys.service;

import com.tlh.sys.entity.User;

import java.util.Collection;

/**
 * Created by hup on 2016/12/24.
 */
public interface UserService {

    /**
     * 用户验证
     * @param userName
     * @return
     */
    User validateUserInfo(String userName);

    /**
     * 获取用户的角色信息
     * @param userName
     * @return
     */
    Collection<String> findRoles(String userName);

    /**
     * 获取用户的权限信息
     * @param userName
     * @return
     */
    Collection<String> findPermissions(String userName);

    /**
     * 保存用户信息
     * @param user
     * @return
     * @throws Exception
     */
    boolean saveUserInfo(User user) throws Exception;

    /**
     * 删除用户
     * @param user
     * @return
     */
    boolean deleteUserInfo(User user) throws Exception;

    /**
     * 更新用户
     * @param user
     * @return
     * @throws Exception
     */
    boolean updateUserInfo(User user) throws Exception;

}
