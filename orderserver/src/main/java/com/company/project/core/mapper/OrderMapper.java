package com.company.project.core.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.pojo.TaskInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.*;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
public interface OrderMapper<OrderPojo>  {


    String setOrder(OrderPojo model);
    List<OrderPojo> getOrderList(List<String> orderIds,String searchWord);
    PageInfo getOrderList(List<Map> taskInfoList, String searchWord, Integer page, Integer size);
    OrderPojo getOrderInfo(String orderId);
    boolean deleteOrder(String id);
    String getProcessDefinitionId();
    List<OrderPojo> getOrderByTime(Date startTime,Date endTime);
    boolean doAfterCommit(ClaimInfo claimInfo , TaskInfo taskInfo);
    boolean setProInsId(String orderId , String proInsId);
    boolean setOrderStatus(String orderId , String orderStatus);
    PageInfo getOrderListByQueryCriteria(Map queryCriteria, Integer page, Integer size);
}
