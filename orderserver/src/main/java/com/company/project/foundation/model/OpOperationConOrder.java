package com.company.project.foundation.model;

import javax.persistence.*;

@Table(name = "op_operation_con_order")
public class OpOperationConOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "op_operation_id")
    private String opOperationId;

    @Column(name = "con_order_id")
    private String conOrderId;

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
     * @return op_operation_id
     */
    public String getOpOperationId() {
        return opOperationId;
    }

    /**
     * @param opOperationId
     */
    public void setOpOperationId(String opOperationId) {
        this.opOperationId = opOperationId;
    }

    /**
     * @return con_order_id
     */
    public String getConOrderId() {
        return conOrderId;
    }

    /**
     * @param conOrderId
     */
    public void setConOrderId(String conOrderId) {
        this.conOrderId = conOrderId;
    }
}