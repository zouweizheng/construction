package com.company.project.common.web;


import com.company.project.common.util.TypeReflect;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Result waitPerson(@RequestParam String orderType ,@RequestAttribute String userId,@RequestAttribute String tid,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "0") String pagination,@RequestParam(defaultValue = "") String searchWord) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List<TaskAndOrder> listResult ;
        if("0".equals(pagination)){
            listResult = orderService.getPersonWait(userId, tid,searchWord);
            return ResultGenerator.genSuccessResult(listResult);
        }
        else{
            //PageHelper.startPage(page, size);
            PageInfo pageInfo = orderService.getPersonWait(userId, tid,searchWord,page,size);
            //PageInfo pageInfo = new PageInfo(listResult);
            return ResultGenerator.genSuccessResult(pageInfo);
        }
    }

    @GetMapping("/groupWait")
    public Result groupWait(@RequestParam String orderType ,@RequestAttribute String userId,@RequestAttribute String tid,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "0") String pagination,@RequestParam(defaultValue = "") String searchWord) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List<TaskAndOrder> listResult ;
        if("0".equals(pagination)){
            listResult = orderService.getGroupWait(userId, tid,searchWord);
            return ResultGenerator.genSuccessResult(listResult);
        }
        else{
            PageInfo pageInfo = orderService.getGroupWait(userId, tid,searchWord,page,size);
            return ResultGenerator.genSuccessResult(pageInfo);
        }
    }

    @GetMapping("/nodeOrder")
    public Result allOrder(@RequestParam String orderType ,String nodeName ,@RequestAttribute String userId,@RequestAttribute String tid) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List listResult = orderService.getNodeFinish(nodeName,tid);
        return ResultGenerator.genSuccessResult(listResult);
    }
    @GetMapping("/finishPerson")
    public Result finishPerson(@RequestParam String orderType,@RequestAttribute String userId,@RequestAttribute String tid,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "0") String pagination,@RequestParam(defaultValue = "") String searchWord) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        List listResult;
        if("0".equals(pagination)){
            listResult = orderService.getPersonFinish(userId, tid,searchWord);
            return ResultGenerator.genSuccessResult(listResult);
        }
        else{
            PageInfo pageInfo = orderService.getPersonFinish(userId, tid,searchWord,page,size);
            return ResultGenerator.genSuccessResult(pageInfo);
        }
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

    @RequestMapping(value="/getAllOrderWithPage", method = RequestMethod.POST)
    public Result getAllOrderWithPage(@RequestParam String orderType,@RequestBody Map queryCriteria, @RequestAttribute String userId, @RequestAttribute String tid,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType),AbstractService.class);
        PageInfo pageInfo = orderService.getOrders(queryCriteria,page,size);
        return ResultGenerator.genSuccessResult(pageInfo);
    }



}
