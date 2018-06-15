package com.nfjd.controller;

import com.alibaba.fastjson.JSONObject;
import com.nfjd.config.ActivitiConfig;
import com.nfjd.util.ActivitiUtil;
import com.nfjd.util.DateTypeTran;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping(value="/test")




public class TestController {

    private static final Logger log= LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private ActivitiConfig activitiConfig;

    //private String activitiRestUrl=activitiConfig.getActivitiRestUrl();

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test()
    {
        return "just test";
    }

    @RequestMapping(value="/getLog",method = RequestMethod.POST)
    public void getLog(@RequestParam String Tid,@RequestBody Map variables)
    {
        log.info("[{}][{}][{}]:{}|processKey:{}|orderId:{}|userId:{}",
                DateTypeTran.getNowTime(),Tid,"Activiti","startprocess",variables);
        if(log.isDebugEnabled()){
            log.debug("bug！");
        }
    }

    @RequestMapping(value="/getpconfig")
    public String getConfig()
    {
        return "ActivitiRestUrl: "+activitiConfig.getActivitiRestUrl()+", HotelOrderRestUrl: "+
                activitiConfig.getHotelOrderRestUrl()+", ProcessId: "+activitiConfig.getProcessId();
    }

    @RequestMapping(value="/getproperties")
    public JSONObject getProperties()
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        return ActivitiUtil.getProperties(url,"kermit","kermit");
    }

    @RequestMapping(value="/startprocess")
    public String startProcess()
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        return ActivitiUtil.startProcessInstance("process:5:17155","jkjkjkj",url,"kermit","kermit");
    }

    @RequestMapping(value="/getvariables")
    public JSONObject getVariables()
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        return ActivitiUtil.getVariables("20061",url,"kermit","kermit");
    }

    @RequestMapping(value="/querycandidatetask")
    public JSONObject queryCandidateTask()
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        return ActivitiUtil.queryCandidateTask("test1",url,"kermit","kermit");
    }

    @RequestMapping(value="/queryassignedtask")
    public JSONObject queryAssignedTask()
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        return ActivitiUtil.queryCandidateTask("zouweizheng",url,"kermit","kermit");
    }

    @RequestMapping(value="/claimtask")
    public String claimTask(@RequestParam String taskId)
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        return ActivitiUtil.claimTask("zouweizheng",taskId,url,"kermit","kermit");
    }

    @RequestMapping(value="/completetask")
    public String completeTask(@RequestParam String taskId)
    {
        String url="http://jiqun.nj-itc.com.cn:10001/activiti-rest";
        List<Map<String,String>> variables=new ArrayList<Map<String, String>>();
        return ActivitiUtil.completeTask(taskId, variables,url,"kermit","kermit");
    }

}
