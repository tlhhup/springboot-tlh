package com.tlh.sys.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.tlh.sys.security.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hup on 2016/12/25.
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private TlhProperties mTlhProperties;

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition=new DefaultShiroFilterChainDefinition();
        shiroFilterChainDefinition.addPathDefinition("/login","authc");
        shiroFilterChainDefinition.addPathDefinition("/logout","anon");
        return shiroFilterChainDefinition;
    }

    @Bean
    Realm realm() {
        CustomRealm customRealm = new CustomRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(mTlhProperties.getHashIterations());
        customRealm.setCredentialsMatcher(credentialsMatcher);
        return customRealm;
    }

    @Bean
    ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
