package com.company.project.common.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by ZouWeizheng on 2017-11-20.
 */
@ConfigurationProperties( prefix = "token")
@Component
@PropertySource("classpath:application.properties")
public class TokenPojo {

    private String salt="jksjdkfsdn";
    private String expirationTime;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }
}
