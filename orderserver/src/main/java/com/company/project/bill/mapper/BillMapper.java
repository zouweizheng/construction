package com.company.project.bill.mapper;

import com.company.project.bill.pojo.BillPojo;
import com.company.project.bill.service.BillService;
import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.mapper.OrderImplMapper;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.core.util.GenerateSeriesID;
import com.company.project.core.util.MapToObjectUtil;
import com.company.project.foundation.model.*;
import com.company.project.foundation.service.BiOrderOpOperationService;
import com.company.project.foundation.service.BiOrderService;
import com.company.project.foundation.service.ConOrderService;
import com.company.project.foundation.service.OpOperationService;
import com.company.project.operation.pojo.OperationPojo;
import com.company.project.test.TestService;
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
@Service("BillMapper")
public class BillMapper extends OrderImplMapper<BillPojo> {

    @Autowired
    BiOrderService biOrderService;

    @Autowired
    TestService testService;

    @Autowired
    BiOrderOpOperationService biOrderOpOperationService;

    @Autowired
    OpOperationService opOperationService;

    @Override
    public String setOrder(BillPojo billPojo) {
        BiOrder biOrder = billPojo.getBiOrder();
        if(null==biOrder.getBillId()||"".equals(biOrder.getBillId())){
            biOrder.setBillId(GenerateSeriesID.createId("bi"));
            biOrderService.save(biOrder);
        }
        else {
            biOrderService.update(biOrder);
        }
        return biOrder.getBillId();
    }

    @Override
    public List<BillPojo> getOrderList(List<String> Ids,String searchName) {
        List<BillPojo> billPojoList = new ArrayList<>();
        for(String orderId : Ids){
            BillPojo billPojo = getOrderInfo(orderId);
            if(null==billPojo) continue;
            billPojoList.add(billPojo);
        }
        return billPojoList;
    }

    @PostConstruct
    public void init(){
        System.out.println("test");
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
        BiOrder biOrder = new BiOrder();
        Condition condition = new Condition(biOrder.getClass());
        condition.and().andIn("billId",orderIds);
        if(!"".equals(searchWord)){
            condition.and().orLike("orderId","%"+searchWord+"%").orLike("orderName","%"+searchWord+"%").orLike("belongPersonName","%"+searchWord+"%").orLike("belongPersonId","%"+searchWord+"%").orLike("createPersonId","%"+searchWord+"%");
        }
        //condition.setOrderByClause("create_time desc limit " + startNum + "," + size);
        condition.setOrderByClause("create_time desc");
        PageHelper.startPage(page, size);
        List<BiOrder> biOrderList = new ArrayList<>();
        try {
            biOrderList = biOrderService.findByCondition(condition);
        }catch (Exception e){
        }
        PageInfo pageInfo = new PageInfo(biOrderList);
        //3、封装具体信息
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        for(BiOrder biOrderGet : biOrderList){
            String orderId = biOrderGet.getBillId();
            TaskAndOrder taskAndOrder = new TaskAndOrder();
            taskAndOrder.setOrderPojo(getOrderInfo(orderId));
            taskAndOrder.setTaskInfo(taskInfoMap.get(orderId));
            taskAndOrder.setOrderId(orderId);
            taskAndOrderList.add(taskAndOrder);
        }
        //4、封装分页信息
        pageInfo.setList(taskAndOrderList);
        return pageInfo;
    }

    @Override
    public BillPojo getOrderInfo(String id) {
        BiOrder biOrder = biOrderService.findBy("billId" , id);
        BillPojo billPojo =new BillPojo();
        if(null==biOrder) return billPojo;
        billPojo.setBiOrder(biOrder);
        billPojo.setId(id);
        //查找关联表
        BiOrderOpOperation biOrderOpOperation = new BiOrderOpOperation();
        Condition condition = new Condition(biOrderOpOperation.getClass());
        condition.and().andEqualTo("biOrderId",id);
        List<BiOrderOpOperation> biOrderOpOperationList = biOrderOpOperationService.findByCondition(condition);
        if(null==biOrderOpOperation||0==biOrderOpOperationList.size()) return billPojo;
        //查找关联的对单
        List<OpOperation> opOperationList = new ArrayList<OpOperation>();
        for(BiOrderOpOperation biOrderOpOperation1 : biOrderOpOperationList){
            OpOperation opOperation = opOperationService.findBy("id",biOrderOpOperation1.getOpOperationId());
            if(!(null==opOperation)) opOperationList.add(opOperation);
        }
        billPojo.setOpOperationList(opOperationList);
        return billPojo;
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
    public boolean doAfterCommit(ClaimInfo claimInfo, TaskInfo taskInfo) {
        return true;
    }

    @Override
    public boolean setProInsId(String orderId, String proInsId) {
        boolean status = false;
        try {
            BiOrder biOrder = biOrderService.findBy("billId",orderId);
            biOrder.setProcessInstanceId(proInsId);
            biOrderService.update(biOrder);
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
            BiOrder biOrder = biOrderService.findBy("billId",orderId);
            biOrder.setBillStatus(orderStatus);
            biOrderService.update(biOrder);
            status = true;
        }catch (Exception e){
            System.console().printf(e.getMessage());
        }
        return status;
    }
    @Override
    public List<BillPojo> getOrderByTime(Date startTime, Date endTime) {
        BiOrder biOrder = new BiOrder();
        Condition condition = new Condition(biOrder.getClass());
        condition.and().andBetween("createTime",startTime,endTime);
        List<BiOrder> biOrderList = biOrderService.findByCondition(condition);
        List<BillPojo> billPojoList = new ArrayList<>();
        for(BiOrder biOrderGet : biOrderList){
            BillPojo billPojo = getOrderInfo(biOrderGet.getBillId());
            billPojoList.add(billPojo);
        }
        return billPojoList;
    }



}
