package com.company.project.core.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.foundation.model.ConOrder;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
public  class OrderImplMapper<OrderPojo> implements OrderMapper<OrderPojo> {




    @Override
    public String setOrder(OrderPojo model) {
        return "";
    }

    @Override
    public OrderPojo getOrderInfo(String id) {
        return null;
    }

    @Override
    public List<OrderPojo> getOrderList(List<String> Ids) {
        return null;
    }


    @Override
    public boolean deleteOrder(String id) {
        return false;
    }

    @Override
    public String getProcessDefinitionId() {
        return null;
    }

    @Override
    public List<OrderPojo> getOrderByTime(Date startTime, Date endTime) {
        return null;
    }

    @Override
    public boolean doAfterCommit(ClaimInfo claimInfo, TaskInfo taskInfo) {
        return false;
    }

    @Override
    public boolean setProInsId(String orderId, String proInsId) {
        return false;
    }

    @Override
    public boolean setOrderStatus(String orderId, String orderStatus) {
        return false;
    }
}
