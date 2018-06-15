package com.company.project.foundation.model;

import javax.persistence.*;

@Table(name = "op_order_con_order")
public class OpOrderConOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "op_order_id")
    private String opOrderId;

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