package com.tlh.sys.realm;

import com.tlh.sys.entity.User;
import com.tlh.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService mUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 处理用户认证
        String userName = token.getPrincipal().toString();
        User validateUser = mUserService.validateUserInfo(userName);
        if (validateUser != null) {
            if (validateUser.isEnabled()) {
                return new SimpleAuthenticationInfo(userName, validateUser.getPassword(), getName());
            } else {
                throw new DisabledAccountException("该用户不可用，请联系管理员");
            }
        } else {
            throw new AccountException("用户名或密码错误");
        }
    }

}