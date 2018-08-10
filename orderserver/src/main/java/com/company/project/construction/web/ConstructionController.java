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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Result getTotal(@RequestParam String projectName, @RequestParam(required = false) String motorcadeId,  @RequestParam(required = false) String motorcadeName,  @RequestParam(required = false) String workType,  @RequestParam(required = false) String createPerson,  @RequestParam(required = false) String startTime,  @RequestParam(required = false) String endTime){

        /*ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and();*/
        Map map = new HashMap();
        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTimeDate = formatter.parse(startTime);
            map.put("startTime",startTimeDate);
        } catch (Exception e) {
            map.put("startTime","");
        }
        try {
            Date endTimeDate = formatter.parse(endTime);
            map.put("endTime",endTimeDate);
        } catch (Exception e) {
            map.put("endTime","");
        }*/
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("projectName",projectName);
        map.put("motorcadeId",motorcadeId);
        map.put("motorcadeName",motorcadeName);
        map.put("workType",workType);
        map.put("createPerson",createPerson);
        Map totalInfo = constructionService.getTotalInfo(map);
        DecimalFormat df = new DecimalFormat("#");
        map.put("totalNum",df.format(totalInfo.get("totalNum")).toString()+"元");
        map.put("totalMoney",df.format(totalInfo.get("totalMoney")).toString()+"元");
        //totalInfo.putAll(map);
        /*List returnInfo = new ArrayList();
        DecimalFormat df = new DecimalFormat("#");
        Map projectNameMap = new HashMap();
        projectNameMap.put("lableName","项目名称");
        projectNameMap.put("lableValue",projectName);
        Map motorcadeIdMap = new HashMap();
        motorcadeIdMap.put("lableName","ID");
        motorcadeIdMap.put("lableValue",motorcadeId);
        Map motorcadeNameMap = new HashMap();
        motorcadeNameMap.put("lableName","供应商");
        motorcadeNameMap.put("lableValue",motorcadeName);
        Map workTypeMap = new HashMap();
        workTypeMap.put("lableName","工作类型");
        workTypeMap.put("lableValue",workType);
        Map createPersonMap = new HashMap();
        createPersonMap.put("lableName","创建人");
        createPersonMap.put("lableValue",createPerson);
        Map timeArea = new HashMap();
        timeArea.put("lableName","时间范围");
        timeArea.put("lableValue",startTime+"至"+endTime);
        Map totalNumMap = new HashMap();
        totalNumMap.put("lableName","总数量");
        totalNumMap.put("lableValue",df.format(totalInfo.get("totalNum")).toString()+"元");
        Map totalMoneyMap = new HashMap();
        totalMoneyMap.put("lableName","总金额");
        totalMoneyMap.put("lableValue",df.format(totalInfo.get("totalMoney")).toString()+"元");
        returnInfo.add(projectNameMap);
        returnInfo.add(motorcadeIdMap);
        returnInfo.add(motorcadeNameMap);
        returnInfo.add(workTypeMap);
        returnInfo.add(createPersonMap);
        returnInfo.add(timeArea);
        returnInfo.add(totalNumMap);
        returnInfo.add(totalMoneyMap);*/
        return ResultGenerator.genSuccessResult(map);
    }



}
