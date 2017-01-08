package com.tlh.sys.config;

import com.tlh.sys.security.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hup on 2016/12/25.
 */
@Configuration
public class ShiroConfig {

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition=new DefaultShiroFilterChainDefinition();
        shiroFilterChainDefinition.addPathDefinition("/login","authc");
        return shiroFilterChainDefinition;
    }

    @Bean
    Realm realm() {
        CustomRealm customRealm = new CustomRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(credentialsMatcher);
        return customRealm;
    }

}
