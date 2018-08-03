package com.company.project.construction.web;


import com.company.project.construction.mapper.ConOrderExtMapper;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.construction.service.ConstructionService;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/con")
public class ConstructionController {

    @Autowired
    ConOrderService conOrderService;

    @Autowired
    ConstructionService constructionService;


    @Autowired
    ApplicationContext applicationContext;


    @PostMapping("/setCon")
    public Result setCon(@RequestBody ConstructionPojo constructionPojo) {
        AbstractService constructionService = applicationContext.getBean("ConstructionService",AbstractService.class);
        String orderId = constructionService.setOrder(constructionPojo);
        return ResultGenerator.genSuccessResult(orderId);
    }

    @PostMapping("/insertOrder")
    public ApiResult<TaskAndOrder> insertOrder(@RequestBody ConstructionPojo constructionPojo , @RequestAttribute String tid,@RequestAttribute String userId) throws Exception {
        AbstractService constructionService = applicationContext.getBean("ConstructionService",AbstractService.class);
        Map map = new HashMap<>();
        map.put("createPerson",userId);
        constructionPojo.getConOrder().setCreatePersonId(userId);
        Date date = new Date();
        constructionPojo.getConOrder().setCreateTime(date);
        TaskAndOrder orderId = constructionService.insertModel(constructionPojo,map,tid);
        ApiResult<TaskAndOrder> apiResult = new ApiResult<TaskAndOrder>();
        apiResult.setBody(orderId);
        return apiResult;
    }

    @GetMapping("/printNumIncrease")
    public Result printNumIncrease(@RequestParam String orderId){
        if(null==orderId||"".equals(orderId)){
            return ResultGenerator.genFailResult("入参缺失");
        }
        ConOrder conOrder = conOrderService.findBy("orderId",orderId);
        if(null==conOrder||"".equals(conOrder)){
            return ResultGenerator.genFailResult("找不到单据");
        }
        int printNum = conOrder.getPrintNum();
        conOrder.setPrintNum(++printNum);
        conOrderService.update(conOrder);
        return ResultGenerator.genSuccessResult(conOrder);
    }

    @GetMapping("/getTotal")
    public Result getTotal(){

        /*ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and();*/
        Map map = constructionService.getTotalInfo();
        return ResultGenerator.genSuccessResult(map);
    }



}
