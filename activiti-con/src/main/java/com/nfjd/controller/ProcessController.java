package com.nfjd.controller;

import com.nfjd.config.ActivitiConfig;
import com.nfjd.domain.ACT_HI_ACTINST;
import com.nfjd.mappers.MedalMixMapper;
import com.nfjd.pojo.ApiResult;
import com.nfjd.pojo.TaskInfo;
import com.nfjd.pojo.mypojo.MixTask;
import com.nfjd.service.ActivitiService;
import com.nfjd.util.DateTypeTran;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by ZouWeizheng on 2017-03-15.
 */
@RestController
@RequestMapping(value="/process")
public class ProcessController {

    private static final Logger log= LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    private ActivitiConfig activitiConfig;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private FormService formService;

    @Autowired
    private MedalMixMapper medalMixMapper;



    @ApiOperation(value="启动流程", notes="")
    @ApiImplicitParam(name = "processDefinitionKey", value = "流程名称", required = true, dataType = "String")
    @RequestMapping(value="/startprocess", method = RequestMethod.POST)
    public ApiResult startProcessInstance(@RequestParam String processDefinitionKey, String orderId,@RequestBody Map variables,@RequestParam String Tid){

        //封装需要传递的参数
        identityService.setAuthenticatedUserId("zouweizheng");
        ApiResult apiResult=new ApiResult();
        Map map=new HashMap();
        //启动流程
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, orderId, variables);
            String processInstanceId = processInstance.getId();
            Task myTask = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            log.info("processInstanceId:" + processInstanceId);
            map = activitiService.getTaskResult(myTask);
            map.put("orderId",orderId);
            map.put("processInstanceId",processInstanceId);
        }catch (Exception e){
            apiResult.setErrcode(20010);
            apiResult.setErrmsg("插入错误");
            map.put("exceptionMessage",e.getMessage());
        }
        apiResult.setBody(map);
        return apiResult;
    }


    @RequestMapping(value="/startandclaim", method = RequestMethod.POST)
    public ApiResult startAndClaimProcessInstance(@RequestParam String processDefinitionKey, String orderId, String nextAssigner,String Tid,@RequestBody Map variables){

        //封装需要传递的参数
        log.info("processDefinitionKey:"+processDefinitionKey);
        identityService.setAuthenticatedUserId("zouweizheng");
        ApiResult apiResult=new ApiResult();
        Map map=new HashMap();
        //启动流程
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, orderId, variables);
            String processInstanceId = processInstance.getId();
            String taskId=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult().getId();
            taskService.claim(taskId,nextAssigner);
            log.info("processInstanceId:" + processInstanceId);
            map.put("processInstanceId",processInstanceId);
            map.put("taskId",taskId);
        }catch (Exception e){
            apiResult.setErrcode(20010);
            apiResult.setErrmsg("插入错误");
            map.put("exceptionMessage",e.getMessage());
        }
        apiResult.setBody(map);
        return apiResult;
    }

    /**
     * 签收任务
     * @param getMap
     */
    @RequestMapping(value="/claimtask", method = RequestMethod.POST)
    public void  claimTask(@RequestBody Map getMap,@RequestParam String Tid)
    {
        String userId=getMap.get("userId").toString();
        String taskId=getMap.get("taskId").toString();
        //Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).taskUnassigned().singleResult();
        Task task=taskService.createTaskQuery().taskId(taskId).taskUnassigned().singleResult();
        ApiResult apiResult=new ApiResult();
        if(null!=task) {
            try {
                taskService.claim(task.getId(), userId);
            }catch (Exception e){
                apiResult.setErrcode(20010);
                apiResult.setErrmsg("签收错误");
                Map map=new HashMap();
                map.put("exceptionMessage",e.getMessage());
                apiResult.setBody(map);
            }
        }
        else{
            apiResult.setErrcode(10001);
            apiResult.setErrmsg("未找到待办单");
        }
    }



    //确认任务
    @RequestMapping(value="/completetask", method = RequestMethod.POST)
    public ApiResult<Map> completeTask(@RequestBody Map getMap,@RequestParam String Tid )
    {
        String taskId=getMap.get("taskId").toString();
        Map variables=(Map) getMap.get("variables");
        ApiResult apiResult=new ApiResult();
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        if(null==task||"".equals(task)) {
            apiResult.setErrcode(10001);
            apiResult.setErrmsg("未找到待办单");
            return apiResult;
        }
        try {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            taskService.complete(taskId, variables);
            task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            String orderId = processInstance.getBusinessKey();
            Map activitiMap = activitiService.getTaskResult(task);
            activitiMap.put("orderId",orderId);
            apiResult.setBody(activitiMap);
        }catch (Exception e){
            apiResult.setErrcode(20010);
            apiResult.setErrmsg("确认错误");
            Map map=new HashMap();
            map.put("exceptionMessage",e.getMessage());
            apiResult.setBody(map);
        }
        return apiResult;
    }


    //签收和完成任务
    @RequestMapping(value="/claimandcompletetask", method = RequestMethod.POST)
    public void claimAndCompleteTask(@RequestBody Map getMap ,@RequestParam String Tid)
    {
        String userId=getMap.get("userId").toString();
        String taskId=getMap.get("taskId").toString();
        Map variables=(Map) getMap.get("variables");
       // String processInstanceId=getMap.get("processInstanceId").toString();
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId=task.getProcessInstanceId();
        ApiResult apiResult=new ApiResult();
        if(null!=task) {
            try {
                taskService.claim(task.getId(), userId);
                List<Task> nextTaskList=taskService.createTaskQuery().processInstanceId(processInstanceId).list();
                Task nextTask=nextTaskList.get(0);
                taskService.complete(nextTask.getId(), variables);
            }catch (Exception e){
                apiResult.setErrcode(20010);
                apiResult.setErrmsg("签收错误");
                Map map=new HashMap();
                map.put("exceptionMessage",e.getMessage());
                apiResult.setBody(map);
            }
        }
        else{
            apiResult.setErrcode(10001);
            apiResult.setErrmsg("未找到待办单");
        }
    }

    //确认并指定下一环节接收人
    @RequestMapping(value="/completeandclaimtask", method = RequestMethod.POST)
    public ApiResult completeandclaimTask(@RequestBody Map getMap ,@RequestParam String Tid)
    {
        String userId=getMap.get("userId").toString();
        String nextAssigner=getMap.get("nextAssigner").toString();
        Map variables=(Map) getMap.get("variables");
        String processInstanceId=getMap.get("processInstanceId").toString();
        Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        ApiResult apiResult=new ApiResult();
        if(null!=task) {
            try {
                taskService.complete(task.getId(), variables);
                Task nextTask=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
                taskService.setAssignee(nextTask.getId(),nextAssigner);
            }catch (Exception e){
                apiResult.setErrcode(20010);
                apiResult.setErrmsg("签收错误");
                Map map=new HashMap();
                map.put("exceptionMessage",e.getMessage());
                apiResult.setBody(map);
            }
        }
        else{
            apiResult.setErrcode(10001);
            apiResult.setErrmsg("未找到待办单");
        }
        return apiResult;
    }

    //查找taskId
    @RequestMapping(value="/getTaskIdByInsId", method = RequestMethod.POST)
    public ApiResult getTaskIdByInsId(@RequestBody Map getMap ,@RequestParam String Tid)
    {
        String processInstanceId=getMap.get("processInstanceId").toString();
        Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Map map = new HashMap();
        map.put("taskId",task.getId());
        ApiResult apiResult = new ApiResult();
        apiResult.setBody(map);
        return apiResult;
    }

    //查找taskInfo
    @RequestMapping(value="/getTaskInfoByInsId", method = RequestMethod.POST)
    public ApiResult getTaskInfoByInsId(@RequestBody Map getMap ,@RequestParam String Tid)
    {
        String processInstanceId=getMap.get("processInstanceId").toString();
        Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        ApiResult apiResult = new ApiResult();
        apiResult.setBody(task);
        return apiResult;
    }

    /**
     * 将已签收工单退回组内
     * @param taskId
     */
    @RequestMapping(value = "/clearAssigned", method = RequestMethod.GET)
    public void clearAssigned(@RequestParam String taskId){
        activitiService.clearAssigned(taskId);
    }




}
