package com.company.project.foundation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bi_order")
public class BiOrder {
    /**
     * 基建单号
     */
    @Id
    @Column(name = "bill_id")
    private String billId;

    @Column(name = "bill_name")
    private String billName;

    @Column(name = "bill_type")
    private String billType;

    @Column(name = "bill_status")
    private String billStatus;

    @Column(name = "create_person_id")
    private String createPersonId;

    @Column(name = "create_person_name")
    private String createPersonName;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "project_name")
    private String projectName;

    private Double money;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "process_instance_id")
    private String processInstanceId;

    /**
     * 获取基建单号
     *
     * @return bill_id - 基建单号
     */
    public String getBillId() {
        return billId;
    }

    /**
     * 设置基建单号
     *
     * @param billId 基建单号
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * @return bill_name
     */
    public String getBillName() {
        return billName;
    }

    /**
     * @param billName
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * @return bill_type
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * @return bill_status
     */
    public String getBillStatus() {
        return billStatus;
    }

    /**
     * @param billStatus
     */
    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * @return create_person_id
     */
    public String getCreatePersonId() {
        return createPersonId;
    }

    /**
     * @param createPersonId
     */
    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    /**
     * @return create_person_name
     */
    public String getCreatePersonName() {
        return createPersonName;
    }

    /**
     * @param createPersonName
     */
    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    /**
     * @return project_id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return project_name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return process_instance_id
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * @param processInstanceId
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}