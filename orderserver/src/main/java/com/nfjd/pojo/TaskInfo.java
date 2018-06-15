package com.nfjd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-04-06.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInfo {

    private String taskId;
    private String name;
    private String id;
    private String owner;
    private String assignee;
    private String category;
    private String formkey;
    private Map processVariables;
    private Map taskLocalVariables;
    private String processInstanceId;
    private String businessKey;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormkey() {
        return formkey;
    }

    public void setFormkey(String formkey) {
        this.formkey = formkey;
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





}
