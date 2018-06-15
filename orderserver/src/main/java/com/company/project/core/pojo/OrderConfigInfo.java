package com.company.project.core.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by ZouWeizheng on 2017-11-20.
 */
@ConfigurationProperties( prefix = "construction")
@Component
@PropertySource("classpath:application.properties")
public class OrderConfigInfo {

    private String activitiUrl;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String agentPerson;

    public String getActivitiUrl() {
        return activitiUrl;
    }

    public void setActivitiUrl(String activitiUrl) {
        this.activitiUrl = activitiUrl;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getAgentPerson() {
        return agentPerson;
    }

    public void setAgentPerson(String agentPerson) {
        this.agentPerson = agentPerson;
    }
}
