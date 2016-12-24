package com.tlh;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.Realm;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tlh.sys.realm.CustomRealm;

@SpringBootApplication
@MapperScan(basePackages="com.tlh.**.mapper")
public class TlhApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlhApplication.class, args);
	}

	@Bean
	Realm realm(){
		CustomRealm customRealm=new CustomRealm();
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
		credentialsMatcher.setHashIterations(5);
		customRealm.setCredentialsMatcher(credentialsMatcher);
		return customRealm;
	}
	
}
