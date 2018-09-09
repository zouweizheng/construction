package com.company.project.core.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.foundation.model.ConOrder;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<OrderPojo> getOrderList(List<String> orderIds,String searchWord) {
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

    @Override
    public PageInfo getOrderList(List<Map> taskInfoList, String searchWord, Integer page, Integer size) {
        return null;
    }

    @Override
    public PageInfo getOrderListByQueryCriteria(Map queryCriteria, Integer page, Integer size) {
        return null;
    }
}
