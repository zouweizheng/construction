package com.company.project.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-04-06.
 */
public class TaskInfo {

    private String taskId;
    private String name;
    private String id;
    private String owner;
    private String assignee;
    private Map variables;
    private String processInstanceId;
    private String businessKey;
    private String executionId;
    private String taskCreateTime;
    private String processDefinitionId;
    private String orderId;
    private String formkey;
    private String category;
    private Map processVariables;
    private Map taskLocalVariables;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Map getVariables() {
        return variables;
    }

    public void setVariables(Map variables) {
        this.variables = variables;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFormkey() {
        return formkey;
    }

    public void setFormkey(String formkey) {
        this.formkey = formkey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map processVariables) {
        this.processVariables = processVariables;
    }

    public Map getTaskLocalVariables() {
        return taskLocalVariables;
    }

    public void setTaskLocalVariables(Map taskLocalVariables) {
        this.taskLocalVariables = taskLocalVariables;
    }

    public String getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(String taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }
}
