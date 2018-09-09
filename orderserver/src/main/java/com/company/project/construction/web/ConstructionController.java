package com.company.project.construction.web;


import com.alibaba.fastjson.JSONObject;
import com.company.project.common.pojo.ConfigInfo;
import com.company.project.common.util.TypeReflect;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.construction.service.ConstructionService;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.CodeResult;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.core.service.AbstractService;
import com.company.project.core.util.NewActivitiUtil;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

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
    CacheManager cacheManager;


    @Autowired
    ApplicationContext applicationContext;


    @PostMapping("/setCon")
    public CodeResult<TaskAndOrder> setCon(@RequestBody ConstructionPojo constructionPojo , @RequestAttribute String tid, @RequestAttribute String userId, HttpServletRequest request) throws Exception {
        //随机时延
        //System.out.println( "延时前:"+new Date().toString()  );
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(100) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(   "延时后:"+new Date().toString()   );
        //通过token判断是否1秒内有重复请求
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        Object lastTimeObj = session.getAttribute(token);
        CodeResult<TaskAndOrder> apiResult = new CodeResult<TaskAndOrder>();
        Date nowTime = new Date();
        if(null!=lastTimeObj){
            try{
                System.out.print("token上次时间为："+lastTimeObj);
                Date lastTime = (Date) lastTimeObj;
                Calendar lastTimeCal = Calendar.getInstance();
                lastTimeCal.setTime(lastTime);
                Calendar c = Calendar.getInstance();
                c.setTime(nowTime);
                c.add(Calendar.SECOND, -2);
                if(c.compareTo(lastTimeCal)<0){
                    session.setAttribute(token,nowTime);
                    System.out.print("由token防重复提交命中！");
                    apiResult.setCode(602);
                    apiResult.setMessage("提单过于频繁！");
                    return apiResult;
                }
            }catch (Exception e){
                System.out.print(e.toString());
            }
        }
        session.setAttribute(token,nowTime);
        //判断数据库是否有重复单
        int repeatTime = 10;
        try {
            ConfigInfo repeatTimeConfigInfo = getConfig("RepeatTime");
            String repeatTimeStr = repeatTimeConfigInfo.getConfigValue();
            if(!(null==repeatTimeStr||"".equals(repeatTimeStr))){
                try {
                    repeatTime = Integer.parseInt(repeatTimeStr);
                }catch (Exception e){
                }
            }
        }catch (Exception e){
        }
        if(constructionService.isRepeatOrder(constructionPojo,repeatTime)){
            CodeResult repeatResult = new CodeResult();
            repeatResult.setCode(602);
            repeatResult.setMessage("重复提单");
            return repeatResult;
        }
        //正常业务操作
        AbstractService constructionService = applicationContext.getBean("ConstructionService",AbstractService.class);
        Map map = new HashMap<>();
        map.put("createPerson",userId);
        constructionPojo.getConOrder().setCreatePersonId(userId);
        Date date = new Date();
        constructionPojo.getConOrder().setCreateTime(date);
        TaskAndOrder orderId = constructionService.insertModel(constructionPojo,map,tid);

        apiResult.setBody(orderId);
        return apiResult;
    }

    @PostMapping("/insertOrder")
    public CodeResult<TaskAndOrder> insertOrder(@RequestBody ConstructionPojo constructionPojo , @RequestAttribute String tid, @RequestAttribute String userId, HttpServletRequest request) throws Exception {
        //随机时延
        //System.out.println( "延时前:"+new Date().toString()  );
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(100) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(   "延时后:"+new Date().toString()   );
        //通过token判断是否1秒内有重复请求
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        Object lastTimeObj = session.getAttribute(token);
        CodeResult<TaskAndOrder> apiResult = new CodeResult<TaskAndOrder>();
        Date nowTime = new Date();
        if(null!=lastTimeObj){
            try{
                System.out.print("token上次时间为："+lastTimeObj);
                Date lastTime = (Date) lastTimeObj;
                Calendar lastTimeCal = Calendar.getInstance();
                lastTimeCal.setTime(lastTime);
                Calendar c = Calendar.getInstance();
                c.setTime(nowTime);
                c.add(Calendar.SECOND, -2);
                if(c.compareTo(lastTimeCal)<0){
                    session.setAttribute(token,nowTime);
                    System.out.print("由token防重复提交命中！");
                    apiResult.setCode(602);
                    apiResult.setMessage("提单过于频繁！");
                    return apiResult;
                }
            }catch (Exception e){
                System.out.print(e.toString());
            }
        }
        session.setAttribute(token,nowTime);
        //判断数据库是否有重复单
        int repeatTime = 10;
        try {
            ConfigInfo repeatTimeConfigInfo = getConfig("RepeatTime");
            String repeatTimeStr = repeatTimeConfigInfo.getConfigValue();
            if(!(null==repeatTimeStr||"".equals(repeatTimeStr))){
                try {
                    repeatTime = Integer.parseInt(repeatTimeStr);
                }catch (Exception e){
                }
            }
        }catch (Exception e){
        }
        if(constructionService.isRepeatOrder(constructionPojo,repeatTime)){
            CodeResult repeatResult = new CodeResult();
            repeatResult.setCode(602);
            repeatResult.setMessage("重复提单");
            return repeatResult;
        }
        //正常业务操作
        AbstractService constructionService = applicationContext.getBean("ConstructionService",AbstractService.class);
        Map map = new HashMap<>();
        map.put("createPerson",userId);
        constructionPojo.getConOrder().setCreatePersonId(userId);
        Date date = new Date();
        constructionPojo.getConOrder().setCreateTime(date);
        TaskAndOrder taskAndOrder = constructionService.insertModel(constructionPojo,map,tid);

        taskAndOrder.setOrderPojo(constructionPojo);
        apiResult.setBody(taskAndOrder);
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


    @GetMapping("/getConfig")
    public ConfigInfo getConfig(@RequestParam String configKey){
       return constructionService.getConfig(configKey);
    }

    @GetMapping("/getAnalysisTab")
    public Result getAnalysisTab(@RequestAttribute String userId){
        return ResultGenerator.genSuccessResult(constructionService.getAnalysisTab(userId));
    }

    @GetMapping("/deleteCon")
    public Result deleteCon(@RequestAttribute String userId, @RequestParam Integer id, @RequestParam String deleteReason) {
        Boolean isDel = null;
        try {
            isDel = constructionService.deleteCon(userId,id,deleteReason);
        } catch (Exception e) {
            isDel = false;
        }
        if(isDel){
            return ResultGenerator.genSuccessResult("删除成功！");
        }
        else return ResultGenerator.genFailResult("删除失败！");
    }

    @GetMapping("/getAnalysis")
    public Result getTotal(@RequestParam String projectName, @RequestParam(required = false) String motorcadeId,  @RequestParam(required = false) String motorcadeName,  @RequestParam(required = false) String feeType,@RequestParam(required = false) String workType,  @RequestParam(required = false) String createPersonName,  @RequestParam(required = false) String startTime,  @RequestParam(required = false) String endTime){

        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("projectName",projectName);
        map.put("motorcadeId",motorcadeId);
        map.put("motorcadeName",motorcadeName);
        map.put("workType",workType);
        map.put("feeType",feeType);
        map.put("createPersonName",createPersonName);
        Map totalInfo = constructionService.getTotalInfo(map);
        DecimalFormat df = new DecimalFormat("#");
        map.put("totalNum",df.format(totalInfo.get("totalNum")).toString());
        map.put("totalMoney",df.format(totalInfo.get("totalMoney")).toString()+"元");
        return ResultGenerator.genSuccessResult(map);
    }

    @PostMapping("/getTotalInfoGroupByWorkType")
    public Result getTotalInfoGroupByWorkType(@RequestBody Map queryCriteria){
        List totalInfos = constructionService.getTotalInfoGroupByWorkType(queryCriteria);
        return ResultGenerator.genSuccessResult(totalInfos);
    }

    @RequestMapping(value="/getAllCon", method = RequestMethod.POST)
    public Result getAllCon(@RequestBody Map queryCriteria,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {

        PageHelper.startPage(page, size);
        List totalInfos = constructionService.getAllCon(queryCriteria);
        PageInfo pageInfo = new PageInfo(totalInfos);
        pageInfo.setList(totalInfos);
        return ResultGenerator.genSuccessResult(pageInfo);
    }



}
