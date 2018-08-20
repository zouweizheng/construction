package com.company.project.foundation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "con_order_del_record")
public class ConOrderDelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "delete_time")
    private Date deleteTime;

    @Column(name = "delete_person")
    private String deletePerson;

    @Column(name = "delete_reason")
    private String deleteReason;

    @Column(name = "project_name")
    private String projectName;

    private Double money;

    private Double num;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "machine_type")
    private String machineType;

    @Column(name = "work_type")
    private String workType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "deal_type")
    private String dealType;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return delete_time
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * @param deleteTime
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * @return delete_person
     */
    public String getDeletePerson() {
        return deletePerson;
    }

    /**
     * @param deletePerson
     */
    public void setDeletePerson(String deletePerson) {
        this.deletePerson = deletePerson;
    }

    /**
     * @return delete_reason
     */
    public String getDeleteReason() {
        return deleteReason;
    }

    /**
     * @param deleteReason
     */
    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
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
     * @return num
     */
    public Double getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * @return unit_price
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return machine_type
     */
    public String getMachineType() {
        return machineType;
    }

    /**
     * @param machineType
     */
    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    /**
     * @return work_type
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType
     */
    public void setWorkType(String workType) {
        this.workType = workType;
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
     * @return deal_type
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * @param dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
}