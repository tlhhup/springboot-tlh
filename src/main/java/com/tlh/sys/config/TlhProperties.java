package com.tlh.sys.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hup on 2017/1/8.
 */
@ConfigurationProperties(prefix = "tlh")
public class TlhProperties {

    //密码hash次数
    private int hashIterations=5;

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(String hashIterations) {
        this.hashIterations = Integer.parseInt(hashIterations);
    }
}
