package com.company.project.core.service;


import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.pojo.OrderPojo;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.pojo.TaskInfo;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T,V> {
    TaskAndOrder insertModel(T model,Map variables,String Tid) throws Exception;//插入工单
    TaskAndOrder modifyModel(T model,Map variables,String Tid);//修改工单
    List<TaskAndOrder>  getGroupWait(String userId , String Tid,String searchWord);//获取本组待办
    PageInfo getGroupWait(String userId, String Tid, String searchWord, Integer page, Integer size);//获取本组待办
    List<TaskAndOrder> getPersonWait(String userId,  String Tid,String searchWord);//获取本人待办
    PageInfo getPersonWait(String userId, String Tid, String searchWord, Integer page, Integer size);//获取本人待办
    List<TaskAndOrder>  getPersonCreate(String userId,String Tid);//获取本人创建
    List<TaskAndOrder>  getPersonFinish(String userId,String Tid,String searchWord);//获取本人已办
    PageInfo getPersonFinish(String userId, String Tid, String searchWord, Integer page, Integer size);//获取本人已办
    List<TaskAndOrder>  getNodeWait(String nodeName,String Tid);//获取节点所有待办
    List<TaskAndOrder>  getNodePersonWait(String nodeName, String userId,String Tid);//获取节点本人待办
    List<TaskAndOrder>  getNodeFinish(String nodeName,String Tid);//获取节点完结待办
    Boolean claim(ClaimInfo claimInfo, String Tid);//签收工单
    TaskInfo commit(ClaimInfo claimInfo, String Tid) throws Exception;//签收工单
    TaskInfo commitAndClaim(ClaimInfo claimInfo, String Tid);//签收工单
    String getNameTest(String fieldName, T model);
    String getMethodTest(String mothodName, T model) throws IllegalAccessException, InstantiationException;
    String setOrder(OrderPojo orderPojo);//操作订单
    Map getOrderDetail(String orderId , String taskId,String userId ,String Tid);//获取工单详情
    List<TaskAndOrder>  getOrderByTime(Date startTime , Date endTime);//获取条件范围内的单据
    PageInfo getOrders(Map queryCriteria, Integer page, Integer size);//获取条件范围内的单据

}
