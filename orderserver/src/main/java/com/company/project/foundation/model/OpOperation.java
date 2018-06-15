package com.company.project.foundation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "op_operation")
public class OpOperation {
    /**
     * 基建单号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operation_name")
    private String operationName;

    /**
     * 审批状态
     */
    @Column(name = "operation_status")
    private String operationStatus;

    @Column(name = "op_order_id")
    private String opOrderId;

    @Column(name = "fee_type")
    private String feeType;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "belong_person_id")
    private String belongPersonId;

    @Column(name = "belong_person_name")
    private String belongPersonName;

    private Double money;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_invoice")
    private Integer isInvoice;

    private Double discount;

    private Double deduction;

    @Column(name = "op_order_name")
    private String opOrderName;

    /**
     * 付款状态
     */
    @Column(name = "pay_status")
    private String payStatus;

    /**
     * 绑定账单号
     */
    @Column(name = "bill_order_id")
    private String billOrderId;

    /**
     * 获取基建单号
     *
     * @return id - 基建单号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置基建单号
     *
     * @param id 基建单号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return operation_name
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @param operationName
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 获取审批状态
     *
     * @return operation_status - 审批状态
     */
    public String getOperationStatus() {
        return operationStatus;
    }

    /**
     * 设置审批状态
     *
     * @param operationStatus 审批状态
     */
    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    /**
     * @return op_order_id
     */
    public String getOpOrderId() {
        return opOrderId;
    }

    /**
     * @param opOrderId
     */
    public void setOpOrderId(String opOrderId) {
        this.opOrderId = opOrderId;
    }

    /**
     * @return fee_type
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * @param feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
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
     * @return belong_person_id
     */
    public String getBelongPersonId() {
        return belongPersonId;
    }

    /**
     * @param belongPersonId
     */
    public void setBelongPersonId(String belongPersonId) {
        this.belongPersonId = belongPersonId;
    }

    /**
     * @return belong_person_name
     */
    public String getBelongPersonName() {
        return belongPersonName;
    }

    /**
     * @param belongPersonName
     */
    public void setBelongPersonName(String belongPersonName) {
        this.belongPersonName = belongPersonName;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return is_invoice
     */
    public Integer getIsInvoice() {
        return isInvoice;
    }

    /**
     * @param isInvoice
     */
    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    /**
     * @return discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return deduction
     */
    public Double getDeduction() {
        return deduction;
    }

    /**
     * @param deduction
     */
    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    /**
     * @return op_order_name
     */
    public String getOpOrderName() {
        return opOrderName;
    }

    /**
     * @param opOrderName
     */
    public void setOpOrderName(String opOrderName) {
        this.opOrderName = opOrderName;
    }

    /**
     * 获取付款状态
     *
     * @return pay_status - 付款状态
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置付款状态
     *
     * @param payStatus 付款状态
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取绑定账单号
     *
     * @return bill_order_id - 绑定账单号
     */
    public String getBillOrderId() {
        return billOrderId;
    }

    /**
     * 设置绑定账单号
     *
     * @param billOrderId 绑定账单号
     */
    public void setBillOrderId(String billOrderId) {
        this.billOrderId = billOrderId;
    }
}