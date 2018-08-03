package com.company.project.construction.service;


import com.company.project.construction.mapper.ConOrderExtMapper;
import com.company.project.construction.mapper.ConstructionMapper;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.dao.ConOrderMapper;
import com.company.project.foundation.model.ConOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/10/31.
 */
@Service("ConstructionService")
public class ConstructionService extends AbstractService<ConstructionPojo,ConstructionMapper>{

    @Autowired
    ApplicationContext applicationContext;


    /*public List<ConOrder> findConOrder(String projectName, String motorcadeId, String motorcadeName, String workType, String createPerson, String startTime, String endTime){
        ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and().andEqualTo("",)
        List<ConOrder> conOrderService.findByCondition()
    }

    public Map getTotalInfo(List<ConOrder> conOrders){
        for
    }*/




}
