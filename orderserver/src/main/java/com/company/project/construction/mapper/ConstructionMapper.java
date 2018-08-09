package com.company.project.construction.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.core.util.GenerateSeriesID;
import com.company.project.core.util.MapToObjectUtil;
import com.company.project.test.TestService;
import com.company.project.core.mapper.OrderImplMapper;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Administrator on 2017/10/29.
 */
@Service("ConstructionMapper")
public class ConstructionMapper extends OrderImplMapper<ConstructionPojo> {

    @Autowired
    ConOrderService conOrderService;

    @Autowired
    TestService testService;

    @Override
    public String setOrder(ConstructionPojo constructionPojo) {
        ConOrder conOrder = constructionPojo.getConOrder();
        if(null==conOrder.getId()||"".equals(conOrder.getId())){
            conOrder.setOrderId(GenerateSeriesID.createId(""));
            conOrderService.save(conOrder);
        }
        else {
            conOrderService.update(conOrder);
        }
        return conOrder.getOrderId().toString();
    }

    @Override
    public List<ConstructionPojo> getOrderList(List<String> orderIds,String searchWord) {

       // conOrderService.findById()
        if(0==orderIds.size()) return null;
        ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and().andIn("orderId",orderIds);
        if(!"".equals(searchWord)){
            condition.and().orLike("orderId","%"+searchWord+"%").orLike("orderName","%"+searchWord+"%").orLike("projectName","%"+searchWord+"%").orLike("motorcadeId","%"+searchWord+"%").orLike("motorcadeId","%"+searchWord+"%");
        }
        condition.setOrderByClause("create_time desc");
        List<ConOrder> conOrderList = conOrderService.findByCondition(condition);
        List<ConstructionPojo> constructionPojoList = new ArrayList<>();
        for(ConOrder conOrderGet : conOrderList){
            ConstructionPojo constructionPojo = new ConstructionPojo();
            constructionPojo.setConOrder(conOrderGet);
            constructionPojo.setId(conOrderGet.getOrderId());
            constructionPojoList.add(constructionPojo);
        }
        return constructionPojoList;
    }

    @Override
    public PageInfo getOrderList(List<Map> taskInfoList, String searchWord, Integer page, Integer size) {

        //1、获取所有orderId，2、封装单独的taskInfo
        TaskInfo taskInfo = new TaskInfo();
        List<String> orderIds = new ArrayList<>();
        Map<String,TaskInfo> taskInfoMap = new HashMap<>();
        for(Map map : taskInfoList){
            try {
                taskInfo = MapToObjectUtil.mapperObj(map,taskInfo.getClass());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            String orderId = taskInfo.getBusinessKey();
            if("".equals(orderId)||null==orderId) continue;
            orderIds.add(taskInfo.getBusinessKey());
            if(taskInfoMap.containsKey(orderId)) continue;
            taskInfoMap.put(orderId,taskInfo);
        }

        //2、查找信息
        ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and().andIn("orderId",orderIds);
        if(!"".equals(searchWord)){
            condition.and().orLike("orderId","%"+searchWord+"%").orLike("orderName","%"+searchWord+"%").orLike("projectName","%"+searchWord+"%").orLike("motorcadeId","%"+searchWord+"%").orLike("motorcadeId","%"+searchWord+"%");
        }
        //condition.setOrderByClause("create_time desc limit " + startNum + "," + size);
        condition.setOrderByClause("create_time desc");
        PageHelper.startPage(page, size);
        List<ConOrder> conOrderList = new ArrayList<>();
        try {
            conOrderList = conOrderService.findByCondition(condition);
        }catch (Exception e){
        }
        PageInfo pageInfo = new PageInfo(conOrderList);


        //3、封装具体信息
        List<ConstructionPojo> constructionPojoList = new ArrayList<>();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        for(ConOrder conOrderGet : conOrderList){
            ConstructionPojo constructionPojo = new ConstructionPojo();
            constructionPojo.setConOrder(conOrderGet);
            constructionPojo.setId(conOrderGet.getOrderId());
            TaskAndOrder taskAndOrder = new TaskAndOrder();
            taskAndOrder.setOrderPojo(constructionPojo);
            taskAndOrder.setTaskInfo(taskInfoMap.get(conOrderGet.getOrderId().toString()));
            taskAndOrder.setOrderId(conOrderGet.getOrderId());
            taskAndOrderList.add(taskAndOrder);
        }

        //4、封装分页信息
        pageInfo.setList(taskAndOrderList);
        return pageInfo;
    }

    @PostConstruct
    public void init(){
        System.out.println("test");
    }

    @Override
    public ConstructionPojo getOrderInfo(String orderId) {
        ConOrder conOrder = conOrderService.findBy("orderId" , orderId);
        ConstructionPojo constructionPojo =new ConstructionPojo();
        constructionPojo.setConOrder(conOrder);
        if(null==conOrder) return null;
        return constructionPojo;
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
    public List<ConstructionPojo> getOrderByTime(Date startTime, Date endTime) {
        ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and().andBetween("createTime",startTime,endTime);
        List<ConOrder> conOrderList = conOrderService.findByCondition(condition);
        List<ConstructionPojo> constructionPojoList = new ArrayList<>();
        for(ConOrder conOrderGet : conOrderList){
            ConstructionPojo constructionPojo = new ConstructionPojo();
            constructionPojo.setConOrder(conOrderGet);
            constructionPojo.setId(conOrderGet.getOrderId());
            constructionPojoList.add(constructionPojo);
        }
        return constructionPojoList;
    }

    @Override
    public boolean doAfterCommit(ClaimInfo claimInfo, TaskInfo taskInfo) {
       return true;
    }

    @Override
    public boolean setProInsId(String orderId, String proInsId) {
        boolean status = false;
        try {
            ConOrder conOrder = conOrderService.findBy("orderId",orderId);
            conOrder.setProcessInstanceId(proInsId);
            conOrderService.update(conOrder);
            status = true;
        }catch (Exception e){
            System.console().printf(e.getMessage());
        }
        return status;
    }

    @Override
    public boolean setOrderStatus(String orderId, String orderStatus) {
        boolean status = false;
        try {
            ConOrder conOrder = conOrderService.findBy("orderId",orderId);
            conOrder.setOrderStatus(orderStatus);
            conOrderService.update(conOrder);
            status = true;
        }catch (Exception e){
            System.console().printf(e.getMessage());
        }
        return status;
    }
}
