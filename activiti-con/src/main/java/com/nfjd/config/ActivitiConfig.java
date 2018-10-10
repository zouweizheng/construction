package com.nfjd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ZouWeizheng on 2017-03-06.
 */
@ConfigurationProperties(locations = "classpath:config/application.yml", prefix = "activiti")
@Component
public class ActivitiConfig {

    private String activitiRestUrl;
    private String hotelOrderRestUrl;
    private String processId;
    private String password;
    private String agentPerson;

    public String getAgentPerson() {
        return agentPerson;
    }

    public void setAgentPerson(String agentPerson) {
        this.agentPerson = agentPerson;
    }

    public String getActivitiRestUrl() {
        return activitiRestUrl;
    }

    public String getProcessId(){
        return processId;
    }

    public String getPassword(){
        return  password;
    }

    public String getHotelOrderRestUrl(){ return hotelOrderRestUrl;}

    public void setActivitiRestUrl(String activitiRestUrl) {
        this.activitiRestUrl = activitiRestUrl;
    }

    public void setProcessId(String processId){
        this.processId=processId;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setHotelOrderRestUrl(String hotelOrderRestUrl) {this.hotelOrderRestUrl=hotelOrderRestUrl;}
}
