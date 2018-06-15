package com.company.project.operation.pojo;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/15.
 */
public class ClaimInfo {
    private String userId;
    private String taskId;
    private String processDefinitionId;
    private String nextAssigner;
    private Map variables;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getNextAssigner() {
        return nextAssigner;
    }

    public void setNextAssigner(String nextAssigner) {
        this.nextAssigner = nextAssigner;
    }

    public Map getVariables() {
        return variables;
    }

    public void setVariables(Map variables) {
        this.variables = variables;
    }
}
