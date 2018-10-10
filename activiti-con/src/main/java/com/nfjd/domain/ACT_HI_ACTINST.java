package com.nfjd.domain;

import java.util.Date;

public class ACT_HI_ACTINST {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.PROC_DEF_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String procDefId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.PROC_INST_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String procInstId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.EXECUTION_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String executionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.ACT_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String actId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.TASK_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String taskId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.CALL_PROC_INST_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String callProcInstId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.ACT_NAME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String actName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.ACT_TYPE_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String actType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.ASSIGNEE_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String assignee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.START_TIME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.END_TIME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.DURATION_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private Long duration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACT_HI_ACTINST.TENANT_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    private String tenantId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public ACT_HI_ACTINST(String id, String procDefId, String procInstId, String executionId, String actId, String taskId, String callProcInstId, String actName, String actType, String assignee, Date startTime, Date endTime, Long duration, String tenantId) {
        this.id = id;
        this.procDefId = procDefId;
        this.procInstId = procInstId;
        this.executionId = executionId;
        this.actId = actId;
        this.taskId = taskId;
        this.callProcInstId = callProcInstId;
        this.actName = actName;
        this.actType = actType;
        this.assignee = assignee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public ACT_HI_ACTINST() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.ID_
     *
     * @return the value of ACT_HI_ACTINST.ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.ID_
     *
     * @param id the value for ACT_HI_ACTINST.ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.PROC_DEF_ID_
     *
     * @return the value of ACT_HI_ACTINST.PROC_DEF_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getProcDefId() {
        return procDefId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.PROC_DEF_ID_
     *
     * @param procDefId the value for ACT_HI_ACTINST.PROC_DEF_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId == null ? null : procDefId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.PROC_INST_ID_
     *
     * @return the value of ACT_HI_ACTINST.PROC_INST_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getProcInstId() {
        return procInstId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.PROC_INST_ID_
     *
     * @param procInstId the value for ACT_HI_ACTINST.PROC_INST_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId == null ? null : procInstId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.EXECUTION_ID_
     *
     * @return the value of ACT_HI_ACTINST.EXECUTION_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.EXECUTION_ID_
     *
     * @param executionId the value for ACT_HI_ACTINST.EXECUTION_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId == null ? null : executionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.ACT_ID_
     *
     * @return the value of ACT_HI_ACTINST.ACT_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getActId() {
        return actId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.ACT_ID_
     *
     * @param actId the value for ACT_HI_ACTINST.ACT_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setActId(String actId) {
        this.actId = actId == null ? null : actId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.TASK_ID_
     *
     * @return the value of ACT_HI_ACTINST.TASK_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.TASK_ID_
     *
     * @param taskId the value for ACT_HI_ACTINST.TASK_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.CALL_PROC_INST_ID_
     *
     * @return the value of ACT_HI_ACTINST.CALL_PROC_INST_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getCallProcInstId() {
        return callProcInstId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.CALL_PROC_INST_ID_
     *
     * @param callProcInstId the value for ACT_HI_ACTINST.CALL_PROC_INST_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setCallProcInstId(String callProcInstId) {
        this.callProcInstId = callProcInstId == null ? null : callProcInstId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.ACT_NAME_
     *
     * @return the value of ACT_HI_ACTINST.ACT_NAME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getActName() {
        return actName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.ACT_NAME_
     *
     * @param actName the value for ACT_HI_ACTINST.ACT_NAME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.ACT_TYPE_
     *
     * @return the value of ACT_HI_ACTINST.ACT_TYPE_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getActType() {
        return actType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.ACT_TYPE_
     *
     * @param actType the value for ACT_HI_ACTINST.ACT_TYPE_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setActType(String actType) {
        this.actType = actType == null ? null : actType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.ASSIGNEE_
     *
     * @return the value of ACT_HI_ACTINST.ASSIGNEE_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.ASSIGNEE_
     *
     * @param assignee the value for ACT_HI_ACTINST.ASSIGNEE_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee == null ? null : assignee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.START_TIME_
     *
     * @return the value of ACT_HI_ACTINST.START_TIME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.START_TIME_
     *
     * @param startTime the value for ACT_HI_ACTINST.START_TIME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.END_TIME_
     *
     * @return the value of ACT_HI_ACTINST.END_TIME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.END_TIME_
     *
     * @param endTime the value for ACT_HI_ACTINST.END_TIME_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.DURATION_
     *
     * @return the value of ACT_HI_ACTINST.DURATION_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.DURATION_
     *
     * @param duration the value for ACT_HI_ACTINST.DURATION_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACT_HI_ACTINST.TENANT_ID_
     *
     * @return the value of ACT_HI_ACTINST.TENANT_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACT_HI_ACTINST.TENANT_ID_
     *
     * @param tenantId the value for ACT_HI_ACTINST.TENANT_ID_
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}