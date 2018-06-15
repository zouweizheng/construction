package com.company.project.foundation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "op_order")
public class OpOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 基建单号
     */
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "create_person_id")
    private String createPersonId;

    @Column(name = "create_person_name")
    private String createPersonName;

    @Column(name = "belong_person_id")
    private String belongPersonId;

    @Column(name = "belong_person_name")
    private String belongPersonName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_invoice")
    private Integer isInvoice;

    private Double discount;

    private Double deduction;

    @Column(name = "process_instance_id")
    private String processInstanceId;

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
     * 获取基建单号
     *
     * @return order_id - 基建单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置基建单号
     *
     * @param orderId 基建单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return order_name
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return order_type
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return order_status
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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