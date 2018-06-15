package com.company.project.common.web;


import com.company.project.common.util.TypeReflect;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.OrderPojo;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/querry")
public class QuerryController {

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/waitPerson")
    public Result waitPerson(@RequestParam String orderType,@RequestAttribute String userId,@RequestAttribute String tid) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List listResult = orderService.getPersonWait(userId, tid);
        return ResultGenerator.genSuccessResult(listResult);
    }

    @GetMapping("/groupWait")
    public Result groupWait(@RequestParam String orderType ,@RequestAttribute String userId,@RequestAttribute String tid) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List listResult = orderService.getGroupWait(userId, tid);
        return ResultGenerator.genSuccessResult(listResult);
    }

    @GetMapping("/nodeOrder")
    public Result allOrder(@RequestParam String orderType ,String nodeName ,@RequestAttribute String userId,@RequestAttribute String tid) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List listResult = orderService.getNodeFinish(nodeName,tid);
        return ResultGenerator.genSuccessResult(listResult);
    }
    @GetMapping("/finishPerson")
    public Result finishPerson(@RequestParam String orderType,@RequestAttribute String userId,@RequestAttribute String tid) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List listResult = orderService.getPersonFinish(userId, tid);
        return ResultGenerator.genSuccessResult(listResult);
    }

    @RequestMapping(value="/getorderdetail", method = RequestMethod.GET)
    public Result getOrderDetail(@RequestParam(value="taskId",required = false,defaultValue = "0") String taskId, String orderType,String orderId, @RequestAttribute String userId, @RequestAttribute String tid)
    {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        if(null==taskId||"".equals(taskId)||null==orderId||"".equals(orderId)) {
            return ResultGenerator.genFailResult("入参错误");
        }
        Map map = orderService.getOrderDetail(orderId,taskId,userId,tid);
        return ResultGenerator.genSuccessResult(map);
    }

    @RequestMapping(value="/getAllOrder", method = RequestMethod.GET)
    public Result getAllOrder(@RequestParam String orderType,String startTime ,String endTime, @RequestAttribute String userId, @RequestAttribute String tid) {
        if(null==orderType||"".equals(orderType)||null==startTime||"".equals(startTime)||null==endTime||"".equals(endTime)) {
            return ResultGenerator.genFailResult("入参缺失");
        }
        Date startTimeTran;
        Date endTimeTran;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startTimeTran= simpleDateFormat.parse(startTime);
            endTimeTran = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("入参错误");
        }
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List getAllResult =  orderService.getOrderByTime(startTimeTran,endTimeTran);
        return ResultGenerator.genSuccessResult(getAllResult);
    }



}
