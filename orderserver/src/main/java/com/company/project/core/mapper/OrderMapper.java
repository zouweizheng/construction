package com.company.project.core.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.pojo.TaskInfo;
import org.springframework.stereotype.*;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
public interface OrderMapper<OrderPojo>  {


    String setOrder(OrderPojo model);
    List<OrderPojo> getOrderList(List<String> Ids);
    OrderPojo getOrderInfo(String id);
    boolean deleteOrder(String id);
    String getProcessDefinitionId();
    List<OrderPojo> getOrderByTime(Date startTime,Date endTime);
    boolean doAfterCommit(ClaimInfo claimInfo , TaskInfo taskInfo);
    boolean setProInsId(String orderId , String proInsId);
    boolean setOrderStatus(String orderId , String orderStatus);
}
