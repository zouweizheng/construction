package com.company.project.bill.web;


import com.company.project.bill.pojo.BillPojo;
import com.company.project.bill.service.BillService;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.BiOrderOpOperation;
import com.company.project.foundation.model.OpOrderConOrder;
import com.company.project.foundation.service.BiOrderOpOperationService;
import com.company.project.foundation.service.ConOrderService;
import com.company.project.operation.pojo.OperationPojo;
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
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    BiOrderOpOperationService biOrderOpOperationService;


    @PostMapping("/setOp")
    public Result setCon(@RequestBody BillPojo billPojo) {
        AbstractService billService = applicationContext.getBean("BillService",AbstractService.class);
        String orderId = billService.setOrder(billPojo);
        return ResultGenerator.genSuccessResult(orderId);
    }

    @PostMapping("/insertBiOrder")
    public ApiResult<TaskAndOrder> insertOrder(@RequestBody BillPojo billPojo , @RequestAttribute String tid, @RequestAttribute String userId) throws Exception {
        AbstractService billService = applicationContext.getBean("BillService",AbstractService.class);
        Map map = new HashMap<>();
        map.put("createPerson",userId);
        billPojo.getBiOrder().setCreatePersonId(userId);
        Date date = new Date();
        billPojo.getBiOrder().setCreateTime(date);
        TaskAndOrder orderId = billService.insertModel(billPojo,map,tid);
        ApiResult<TaskAndOrder> apiResult = new ApiResult<TaskAndOrder>();
        apiResult.setBody(orderId);
        return apiResult;
    }

    @GetMapping("/addOp")
    public Result addOp(@RequestParam int operationId,String billId) {
        BiOrderOpOperation biOrderOpOperation = new BiOrderOpOperation();
        biOrderOpOperation.setBiOrderId(billId);
        biOrderOpOperation.setOpOperationId(operationId);
        Condition condition = new Condition(biOrderOpOperation.getClass());
        condition.and().andEqualTo("opOperationId",operationId);
        List<BiOrderOpOperation> biOrderOpOperationList = biOrderOpOperationService.findByCondition(condition);
        if(0<biOrderOpOperationList.size()) return ResultGenerator.genFailResult("此单已绑定");
        biOrderOpOperationService.save(biOrderOpOperation);
        biOrderOpOperation = biOrderOpOperationService.findBy("opOperationId",operationId);
        return ResultGenerator.genSuccessResult(biOrderOpOperation);
    }

    @GetMapping("/delOp")
    public Result delOp(@RequestParam int operationId,String billId) {
        BiOrderOpOperation biOrderOpOperation = new BiOrderOpOperation();
        biOrderOpOperation = biOrderOpOperationService.findBy("opOperationId",operationId);
        if(null==biOrderOpOperation) return ResultGenerator.genFailResult("没找到对应单");
        biOrderOpOperationService.deleteById(Integer.parseInt(biOrderOpOperation.getId().toString()));
        return ResultGenerator.genSuccessResult();
    }



}
