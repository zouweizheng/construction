package com.company.project.operation.web;


import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.OpOperation;
import com.company.project.foundation.model.OpOrderConOrder;
import com.company.project.foundation.service.ConOrderService;
import com.company.project.foundation.service.OpOrderConOrderService;
import com.company.project.operation.pojo.OperationPojo;
import com.company.project.operation.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/op")
public class OperationController {

    @Autowired
    ConOrderService conOrderService;

    @Autowired
    OpOrderConOrderService opOrderConOrderService;

    @Autowired
    OperationService operationService;


    @Autowired
    ApplicationContext applicationContext;


    @PostMapping("/setOp")
    public Result setCon(@RequestBody OperationPojo operationPojo) {
        AbstractService operationService = applicationContext.getBean("OperationService",AbstractService.class);
        String orderId = operationService.setOrder(operationPojo);
        return ResultGenerator.genSuccessResult(orderId);
    }

    @PostMapping("/insertOpOrder")
    public ApiResult<TaskAndOrder> insertOrder(@RequestBody OperationPojo operationPojo , @RequestAttribute String tid, @RequestAttribute String userId) throws Exception {
        AbstractService operationService = applicationContext.getBean("OperationService",AbstractService.class);
        Map map = new HashMap<>();
        map.put("createPerson",userId);
        operationPojo.getOpOrder().setCreatePersonId(userId);
        Date date = new Date();
        operationPojo.getOpOrder().setCreateTime(date);
        TaskAndOrder orderId = operationService.insertModel(operationPojo,map,tid);
        ApiResult<TaskAndOrder> apiResult = new ApiResult<TaskAndOrder>();
        apiResult.setBody(orderId);
        return apiResult;
    }

    @GetMapping("/addCon")
    public Result addCon(@RequestParam String opOrderId,String conOrderId) {
        OpOrderConOrder opOrderConOrder = new OpOrderConOrder();
        Condition condition = new Condition(opOrderConOrder.getClass());
        condition.and().andEqualTo("conOrderId",conOrderId);
        List<OpOrderConOrder> opOrderConOrderList = opOrderConOrderService.findByCondition(condition);
        if(0<opOrderConOrderList.size()) return ResultGenerator.genFailResult("此单已绑定");
        opOrderConOrder.setConOrderId(conOrderId);
        opOrderConOrder.setOpOrderId(opOrderId);
        opOrderConOrderService.save(opOrderConOrder);
        opOrderConOrder = opOrderConOrderService.findBy("conOrderId",conOrderId);
        return ResultGenerator.genSuccessResult(opOrderConOrder);
    }

    @GetMapping("/delCon")
    public Result delCon(@RequestParam String opOrderId,String conOrderId) {
        OpOrderConOrder opOrderConOrder = opOrderConOrderService.findBy("conOrderId",conOrderId);
        if(null==opOrderConOrder) return ResultGenerator.genFailResult("没找到对应单");
        opOrderConOrderService.deleteById(Integer.parseInt(opOrderConOrder.getId().toString()));
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/getOp")
    public Result getOp(@RequestParam String projectName) {
        return ResultGenerator.genSuccessResult(operationService.getOpOpration(projectName));
    }





}
