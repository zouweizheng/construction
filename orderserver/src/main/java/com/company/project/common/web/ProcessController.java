package com.company.project.common.web;

import com.company.project.common.util.TypeReflect;
import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.pojo.OrderConfigInfo;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.core.service.AbstractService;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ZouWeizheng on 2017-12-12.
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ApplicationContext applicationContext;

    @PostMapping("/claim")
    public Result claim(@RequestBody ClaimInfo claimInfo, @RequestAttribute String tid ,@RequestAttribute String userId, @RequestParam String orderType) {
        OrderConfigInfo orderConfigInfo = applicationContext.getBean(TypeReflect.getOrderConfigInfoReflect(orderType), OrderConfigInfo.class);
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType), AbstractService.class);
        claimInfo.setUserId(userId);
        claimInfo.setProcessDefinitionId(orderConfigInfo.getProcessDefinitionId());
        Boolean isClaim = orderService.claim(claimInfo,tid);
        return ResultGenerator.genSuccessResult(isClaim);
    }

    @PostMapping("/commit")
    public Result commit(@RequestBody ClaimInfo claimInfo, @RequestAttribute String tid ,@RequestAttribute String userId, @RequestParam String orderType) throws Exception {
        OrderConfigInfo orderConfigInfo = applicationContext.getBean(TypeReflect.getOrderConfigInfoReflect(orderType), OrderConfigInfo.class);
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType), AbstractService.class);
        claimInfo.setUserId(userId);
        claimInfo.setProcessDefinitionId(orderConfigInfo.getProcessDefinitionId());
        TaskInfo taskInfo = orderService.commit(claimInfo,tid);
        return ResultGenerator.genSuccessResult(taskInfo);
    }

    /*@PostMapping("/commitAndClaim")
    public Result commitAndClaim(@RequestBody ClaimInfo claimInfo, @RequestAttribute String tid , @RequestParam String orderType) {
        OrderConfigInfo orderConfigInfo = applicationContext.getBean(TypeReflect.getOrderConfigInfoReflect(orderType), OrderConfigInfo.class);
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType), AbstractService.class);
        claimInfo.setProcessDefinitionId(orderConfigInfo.getProcessDefinitionId());
        TaskInfo taskInfo = orderService.commitAndClaim(claimInfo,tid);
        return ResultGenerator.genSuccessResult(taskInfo);
    }*/

    @PostMapping("/sendBack")
    public Result sendBack(@RequestBody ClaimInfo claimInfo ,@RequestAttribute String userId, @RequestParam String orderType) throws Exception {
        //回退
        OrderConfigInfo orderConfigInfo = applicationContext.getBean(TypeReflect.getOrderConfigInfoReflect(orderType), OrderConfigInfo.class);
        AbstractService orderService = applicationContext.getBean(TypeReflect.getOrderTypeReflect(orderType), AbstractService.class);
        claimInfo.setUserId(userId);
        claimInfo.setProcessDefinitionId(orderConfigInfo.getProcessDefinitionId());
        TaskInfo taskInfo = orderService.commit(claimInfo,"");
        //签收
        String newTaskId = taskInfo.getTaskId();
        claimInfo.setUserId(userId);
        claimInfo.setTaskId(newTaskId);
        claimInfo.setProcessDefinitionId(orderConfigInfo.getProcessDefinitionId());
        Boolean isClaim = orderService.claim(claimInfo,"");
        return ResultGenerator.genSuccessResult(isClaim);
    }
}
