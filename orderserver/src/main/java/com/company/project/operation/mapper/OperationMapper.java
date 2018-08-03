package com.company.project.operation.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.mapper.OrderImplMapper;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.core.util.GenerateSeriesID;
import com.company.project.foundation.model.*;
import com.company.project.foundation.service.ConOrderService;
import com.company.project.foundation.service.OpOperationService;
import com.company.project.foundation.service.OpOrderConOrderService;
import com.company.project.foundation.service.OpOrderService;
import com.company.project.operation.pojo.OperationPojo;
import com.company.project.operation.service.OperationService;
import com.company.project.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
@Service("OperationMapper")
public class OperationMapper extends OrderImplMapper<OperationPojo> {

    @Autowired
    OpOrderService opOrderService;

    @Autowired
    OpOperationService opOperationService;

    @Autowired
    OpOrderConOrderService opOrderConOrderService;

    @Autowired
    ConOrderService conOrderService;

    @Autowired
    TestService testService;

    @Override
    public String setOrder(OperationPojo operationPojo) {
        OpOrder opOrder = operationPojo.getOpOrder();
        if(null==opOrder.getId()||"".equals(opOrder.getId())){
            opOrder.setOrderId(GenerateSeriesID.createId("op"));
            opOrderService.save(opOrder);
        }
        else {
            opOrderService.update(opOrder);
        }
        return opOrder.getOrderId();
    }

    @Override
    public List<OperationPojo> getOrderList(List<String> Ids,String searchName) {
        List<OperationPojo> operationPojoList = new ArrayList<>();
        for(String orderId : Ids){
            OperationPojo operationPojo = getOrderInfo(orderId);
            if(null==operationPojo) continue;
            operationPojoList.add(operationPojo);
        }
        return operationPojoList;
    }

    @PostConstruct
    public void init(){
        System.out.println("test");
    }

    @Override
    public OperationPojo getOrderInfo(String orderId) {
        OpOrder opOrder = opOrderService.findBy("orderId" , orderId);
        if(null==opOrder) return null;
        //查找opOperation
        OpOperation opOperation = new OpOperation();
        Condition condition = new Condition(opOperation.getClass());
        condition.and().andEqualTo("opOrderId",orderId);
        List<OpOperation> opOperationList = opOperationService.findByCondition(condition);
        //查找conorder的ids
        OpOrderConOrder opOrderConOrder = new OpOrderConOrder();
        Condition opConCondition = new Condition(opOrderConOrder.getClass());
        opConCondition.and().andEqualTo("opOrderId",orderId);
        List<OpOrderConOrder> opOrderConOrders = opOrderConOrderService.findByCondition(opConCondition);
        List<String> conIdList = new ArrayList<>();
        for(OpOrderConOrder opOrderConOrderGet:opOrderConOrders){
            conIdList.add(opOrderConOrderGet.getConOrderId().trim().toString());
        }
        //封装
        OperationPojo operationPojo =new OperationPojo();
        operationPojo.setOpOrder(opOrder);
        operationPojo.setOpOperationList(opOperationList);
        operationPojo.setId(opOrder.getOrderId());
        //查找conorder
        if(0==conIdList.size()) return operationPojo;
        ConOrder conOrder = new ConOrder();
        Condition conCondition = new Condition(conOrder.getClass());
        conCondition.and().andIn("orderId",conIdList);
        List<ConOrder> conOrders = conOrderService.findByCondition(conCondition);
        //封装
        operationPojo.setConOrderList(conOrders);
        return operationPojo;
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
    public List<OperationPojo> getOrderByTime(Date startTime, Date endTime) {
        OpOrder opOrder = new OpOrder();
        Condition condition = new Condition(opOrder.getClass());
        condition.and().andBetween("createTime",startTime,endTime);
        List<OpOrder> opOrderList = opOrderService.findByCondition(condition);
        List<OperationPojo> operationPojoList = new ArrayList<>();
        for(OpOrder opOrderGet : opOrderList){
            OperationPojo operationPojo = getOrderInfo(opOrderGet.getOrderId());
            operationPojoList.add(operationPojo);
        }
        return operationPojoList;
    }

    @Override
    public boolean doAfterCommit(ClaimInfo claimInfo, TaskInfo taskInfo) {
        return true;
    }

    @Override
    public boolean setProInsId(String orderId, String proInsId) {
        boolean status = false;
        try {
            OpOrder opOrder = opOrderService.findBy("orderId",orderId);
            opOrder.setProcessInstanceId(proInsId);
            opOrderService.update(opOrder);
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
            OpOrder opOrder = opOrderService.findBy("orderId",orderId);
            opOrder.setOrderStatus(orderStatus);
            opOrderService.update(opOrder);
            status = true;
        }catch (Exception e){
            System.console().printf(e.getMessage());
        }
        return status;
    }


}
