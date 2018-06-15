package com.company.project.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ZouWeizheng on 2017-03-06.
 */
/*@ConfigurationProperties(locations = "classpath:config/application.properties", prefix = "myconfig")*/
@Component
public class MyConfig {

    private String activitiUrl = "http://jiqun.nj-itc.com.cn:6565/activiti";

    public String getActivitiUrl() {
        return activitiUrl;
    }

    public void setActivitiUrl(String activitiUrl) {
        this.activitiUrl = activitiUrl;
    }
}
