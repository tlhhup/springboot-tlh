package com.tlh.sys.security;

import com.tlh.sys.entity.User;
import com.tlh.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService mUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        CacheManager cacheManager = getCacheManager();
        if(cacheManager!=null){
            Cache<Object, Object> sys = cacheManager.getCache("sys");
            if(sys!=null) {//如果存在系统缓存
                //设置角色信息
                Collection<String> roles = (Collection<String>) sys.get("roles");
                if (roles != null) {
                    authorizationInfo.addRoles(roles);
                } else {
                    roles = mUserService.findRoles(userName);
                    sys.put("roles",roles);
                    authorizationInfo.addRoles(roles);
                }
                //设置权限信息
                Collection<String> rights = (Collection<String>) sys.get("rights");
                if(rights!=null){
                    authorizationInfo.addStringPermissions(rights);
                }else{
                    rights=mUserService.findPermissions(userName);
                    sys.put("rights",rights);
                    authorizationInfo.addStringPermissions(rights);
                }
            }
        }else{
            authorizationInfo.addRoles(mUserService.findRoles(userName));
            authorizationInfo.addStringPermissions(mUserService.findPermissions(userName));
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 处理用户认证
        String userName = token.getPrincipal().toString();
        User validateUser = mUserService.validateUserInfo(userName);
        if (validateUser != null) {
            if (validateUser.isEnabled()) {
                return new SimpleAuthenticationInfo(userName, validateUser.getPassword(), ByteSource.Util.bytes(validateUser.getSalt()), getName());
            } else {
                throw new DisabledAccountException("该用户不可用，请联系管理员");
            }
        } else {
            throw new AccountException("用户名或密码错误");
        }
    }

}