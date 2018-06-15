package com.company.project.foundation.model;

import javax.persistence.*;

@Table(name = "bi_order_op_operation")
public class BiOrderOpOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "op_operation_id")
    private Integer opOperationId;

    @Column(name = "bi_order_id")
    private String biOrderId;

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
    public Integer getOpOperationId() {
        return opOperationId;
    }

    /**
     * @param opOperationId
     */
    public void setOpOperationId(Integer opOperationId) {
        this.opOperationId = opOperationId;
    }

    /**
     * @return bi_order_id
     */
    public String getBiOrderId() {
        return biOrderId;
    }

    /**
     * @param biOrderId
     */
    public void setBiOrderId(String biOrderId) {
        this.biOrderId = biOrderId;
    }
}