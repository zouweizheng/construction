package com.company.project.construction.mapper;

import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.core.util.GenerateSeriesID;
import com.company.project.test.TestService;
import com.company.project.core.mapper.OrderImplMapper;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
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
    public List getOrderList(List<String> Ids) {

       // conOrderService.findById()
        if(0==Ids.size()) return null;
        String str = "";
        for (String id : Ids){
            str += (id + ",");
        }
        str = str.substring(0,str.length()-1);
        List<ConOrder> conOrderList = conOrderService.findByIds(str);
        return null;
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
