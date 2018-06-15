package com.company.project.core.pojo;

/**
 * Created by Administrator on 2017/11/15.
 */
public class TaskAndOrder {
    private String orderId;
    private TaskInfo taskInfo;
    private OrderPojo orderPojo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public OrderPojo getOrderPojo() {
        return orderPojo;
    }

    public void setOrderPojo(OrderPojo orderPojo) {
        this.orderPojo = orderPojo;
    }
}
