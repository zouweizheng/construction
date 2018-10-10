package com.nfjd.controller;

import com.alibaba.fastjson.JSONObject;
import com.nfjd.config.ActivitiConfig;
import com.nfjd.pojo.ApiResult;
import com.nfjd.service.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * Created by ZouWeizheng on 2017-03-15.
 */
@RestController
@ApiIgnore
@RequestMapping(value="/activiti")
public class ActivitiController {

    private static final Logger log= LoggerFactory.getLogger(ActivitiController.class);

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

    /**
     * 启动流程
     * @param processKey
     * @param orderId
     * @param userId
     * @return
     */
    @RequestMapping(value="/startprocess", method = RequestMethod.GET)
    public ApiResult startProcessInstance( @RequestParam String processKey, String orderId, String userId){

        //封装需要传递的参数
        Map<String,Object> variables=new HashMap<String, Object>();
        variables.put("orderId",orderId);
        variables.put("createPerson",userId);
        //identityService.setAuthenticatedUserId("zouweizheng");
        ApiResult apiResult=new ApiResult();
        Map map=new HashMap();
        //启动流程
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, orderId, variables);
            String processInstanceId = processInstance.getId();
            String taskId=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult().getId();
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

    @RequestMapping(value="/startprocessnew", method = RequestMethod.POST)
    public ApiResult startProcessInstanceNew(@RequestParam String processKey, String orderId,@RequestBody Map variables){

        //封装需要传递的参数
        log.info("processKey:"+processKey);
        identityService.setAuthenticatedUserId("zouweizheng");
        ApiResult apiResult=new ApiResult();
        Map map=new HashMap();
        //启动流程
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, orderId, variables);
            String processInstanceId = processInstance.getId();
            String taskId=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult().getId();
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
     * 启动并签收工单
     * @param processKey
     * @param orderId
     * @param userId
     * @param nextAssigner
     * @return
     */
    @RequestMapping(value="/startandclaim", method = RequestMethod.GET)
    public ApiResult startAndClaimProcessInstance(@RequestParam String processKey, String orderId, String userId,String nextAssigner){

        //封装需要传递的参数
        Map<String,Object> variables=new HashMap<String, Object>();
        log.info("processKey:"+processKey);
        variables.put("orderId",orderId);
        variables.put("createPerson",userId);
        identityService.setAuthenticatedUserId("zouweizheng");
        ApiResult apiResult=new ApiResult();
        Map map=new HashMap();
        //启动流程
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, orderId, variables);
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

    @RequestMapping(value="/startandclaimnew", method = RequestMethod.POST)
    public ApiResult startAndClaimProcessInstanceNew(@RequestParam String processKey, String orderId, String nextAssigner,@RequestBody Map variables){

        //封装需要传递的参数
        log.info("processKey:"+processKey);
        identityService.setAuthenticatedUserId("zouweizheng");
        ApiResult apiResult=new ApiResult();
        Map map=new HashMap();
        //启动流程
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, orderId, variables);
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
     * 查询本组待办工单
     * @param groupId
     * @param processDefinitionKey
     * @return
     */
    @RequestMapping(value="/querycandidatetask", method = RequestMethod.GET)
    public ApiResult queryCandidateTask(@RequestParam  String groupId,String processDefinitionKey)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskCandidateGroup(groupId).processDefinitionKey(processDefinitionKey).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }


    /**
     * 查询本人待办工单
     * @param userId
     * @param processDefinitionKey
     * @return
     */
    @RequestMapping(value="/queryassignedtask", method = RequestMethod.GET)
    public ApiResult queryAssignedTask(@RequestParam  String userId,String processDefinitionKey)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskAssignee(userId).processDefinitionKey(processDefinitionKey).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }

    /**
     * 用于查询代理人员中的待本人处理工单
     * @param userId
     * @param processDefinitionKey
     * @return
     */
    @RequestMapping(value="/queryassignedtasknew", method = RequestMethod.GET)
    public ApiResult queryAssignedTaskNew(@RequestParam  String userId, String agentPerson, String processDefinitionKey)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskAssignee(agentPerson).processVariableValueEquals("belongPerson",userId).processDefinitionKey(processDefinitionKey).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }


    @RequestMapping(value="/querytask",method = RequestMethod.GET)
    public ApiResult queryTask(@RequestParam String taskId)
    {
        List<Task> task = taskService.createTaskQuery().taskId(taskId).list();
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(task);
        apiResult.setBody(taskList);
        return apiResult;
    }

    @RequestMapping(value="/queryinstance",method = RequestMethod.GET)
    public ApiResult queryInstance(@RequestParam String instanceId)
    {
        List<Task> task = taskService.createTaskQuery().processInstanceId(instanceId).list();
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(task);
        apiResult.setBody(runtimeService.getVariable(instanceId,"orderId"));
        return apiResult;
    }

    /**
     * 查询本人已处理工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    /*@RequestMapping(value="/queryfinishtask", method = RequestMethod.GET)
    public ApiResult queryFinishTask(@RequestParam  String userId,String processDefinitionId)
    {
        List<HistoricActivityInstance> historicResult=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).taskAssignee(userId).list();
        List<Map> orderIdList=new ArrayList<>();
        List<String> processInstanceIdList=new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicResult) {
            String processInstanceId = historicActivityInstance.getProcessInstanceId();
            if (!processInstanceIdList.contains(processInstanceId)) {
                processInstanceIdList.add(processInstanceId);
                Map map=activitiService.getVariables(processInstanceId);
                map.put("processInstanceId",processInstanceId);
                map.put("name",historicActivityInstance.getActivityName());
                map.put("assignee",historicActivityInstance.getAssignee());
                map.put("taskId",historicActivityInstance.getTaskId());
                orderIdList.add(map);
            }
        }
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(orderIdList);
        return apiResult;
    }*/

    @RequestMapping(value="/queryfinishtask", method = RequestMethod.GET)
    public ApiResult queryFinishTask(@RequestParam  String userId,String processDefinitionId)
    {
        List<HistoricActivityInstance> historicResult=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).taskAssignee(userId).list();
        List<Task> tasksss=taskService.createTaskQuery().processVariableValueEquals("createPerson",userId).processDefinitionId(processDefinitionId).list();
        List taskList=activitiService.getTaskList(tasksss);
        List<Map> orderIdList=new ArrayList<Map>();
        List<String> processInstanceIdList=new ArrayList<String>();
        for(Task task:tasksss){
            String processInstanceId=task.getProcessInstanceId();
            processInstanceIdList.add(processInstanceId);
        }
        for (HistoricActivityInstance historicActivityInstance : historicResult) {
            String processInstanceId = historicActivityInstance.getProcessInstanceId();
            if (!processInstanceIdList.contains(processInstanceId)) {
                processInstanceIdList.add(processInstanceId);
                Map map=activitiService.getVariables(processInstanceId);
                map.put("processInstanceId",processInstanceId);
                map.put("name",historicActivityInstance.getActivityName());
                map.put("assignee",historicActivityInstance.getAssignee());
                map.put("taskId",historicActivityInstance.getTaskId());
                orderIdList.add(map);
            }
        }
        orderIdList.addAll(taskList);
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(orderIdList);

        return apiResult;

    }


    @RequestMapping(value="/queryfinishtasknew", method = RequestMethod.GET)
    public ApiResult queryFinishTaskNew(@RequestParam  String userId,String processDefinitionId)
    {
        //historyService.createHistoricVariableInstanceQuery().variableValueEquals("belongPerson",userId).singleResult().getProcessInstanceId();
        //List<HistoricActivityInstance> historicResult=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).taskAssignee(userId).list();
        List<Task> tasksss=taskService.createTaskQuery().processVariableValueEquals("belongPerson",userId).processDefinitionId(processDefinitionId).list();
        List taskList=activitiService.getTaskList(tasksss);
        List<Map> orderIdList=new ArrayList<Map>();
        List<String> processInstanceIdList=new ArrayList<String>();
        for(Task task:tasksss){
            String processInstanceId=task.getProcessInstanceId();
            processInstanceIdList.add(processInstanceId);
        }
        /*for (HistoricActivityInstance historicActivityInstance : historicResult) {
            String processInstanceId = historicActivityInstance.getProcessInstanceId();
            if (!processInstanceIdList.contains(processInstanceId)) {
                processInstanceIdList.add(processInstanceId);
                Map map=activitiService.getVariables(processInstanceId);
                map.put("processInstanceId",processInstanceId);
                map.put("name",historicActivityInstance.getActivityName());
                map.put("assignee",historicActivityInstance.getAssignee());
                map.put("taskId",historicActivityInstance.getTaskId());
                orderIdList.add(map);
            }
        }*/
        orderIdList.addAll(taskList);
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(orderIdList);

        return apiResult;

    }




    /**
     * 查询本节点所有工单
     * @param nodeName
     * @param processDefinitionKey
     * @return
     */
    @RequestMapping(value="/querynodetask", method = RequestMethod.GET)
    public ApiResult queryNodeTask(@RequestParam  String nodeName,String processDefinitionKey)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskName(nodeName).processDefinitionKey(processDefinitionKey).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }

    /**
     * 查询所有工单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryalltask", method = RequestMethod.GET)
    public ApiResult queryAllTask(@RequestParam  String processDefinitionId)
    {
        //List<HistoricActivityInstance> historicResult=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).list();
        List<Map> orderIdList=new ArrayList<Map>();
        List<ProcessInstance> processInstanceList=runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).list();
        List<String> processInstanceIdList=new ArrayList<String>();
        for (ProcessInstance processInstance : processInstanceList) {
            String processInstanceId = processInstance.getProcessInstanceId();
            Map map=activitiService.getVariables(processInstanceId);
            processInstance.isSuspended();
            map.put("processInstanceId",processInstanceId);
            map.put("isEnd",processInstance.isEnded());
            Task singleResult=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            String name=(null==singleResult)?"":singleResult.getName();
            map.put("name", name);
            orderIdList.add(map);
        }
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(orderIdList);
        return apiResult;
    }

    /**
     * 获取工单状态
     * @param processInstanceId
     * @return
     */
    @RequestMapping(value="/getstatus", method = RequestMethod.GET)
    public ApiResult getStatus(@RequestParam String processInstanceId,String userId)
    {
        Map map=new HashMap();
        ApiResult apiResult=new ApiResult();
        /*if(1==historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).finished().count()){
            map.put("name","finish");
            map.put("permit","no");
            apiResult.setBody(map);
            return apiResult;
        }*/
        String agentPerson=activitiConfig.getAgentPerson();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (null == task) return apiResult;
        map = activitiService.getTaskResult(task);
        map.put("processInstanceId",processInstanceId);
        if(taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateOrAssigned(userId).count()>0){
            map.put("permit","yes");
        }
        else if(taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateOrAssigned(agentPerson).processVariableValueEquals("belongPerson",userId).count()>0){
            map.put("permit","yes");
        }
        else{
            map.put("permit","no");
        }
        apiResult.setBody(map);
        return apiResult;
    }


    /**
     * 签收任务
     * @param getMap
     */
    @RequestMapping(value="/claimtask", method = RequestMethod.POST)
    public void  claimTask(@RequestBody Map getMap)
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
    public ApiResult completeTask(@RequestBody Map getMap )
    {
        String userId=getMap.get("userId").toString();
        String taskId=getMap.get("taskId").toString();
        Map variables=(Map) getMap.get("variables");
        //Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        ApiResult apiResult=new ApiResult();
        if(null!=task) {
            try {
                taskService.complete(task.getId(), variables);
            }catch (Exception e){
                apiResult.setErrcode(20010);
                apiResult.setErrmsg("确认错误");
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


    //签收和完成任务
    @RequestMapping(value="/claimandcompletetask", method = RequestMethod.POST)
    public void claimAndCompleteTask(@RequestBody Map getMap )
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
    public ApiResult completeandclaimTask(@RequestBody Map getMap)
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



    //获取整组的成员id
    @RequestMapping(value="/getuserbygroup", method = RequestMethod.GET)
    public ApiResult getUserByGroup(@RequestParam String groupId)
    {
        ApiResult apiResult=new ApiResult();
        List<User> userList=identityService.createUserQuery().memberOfGroup(groupId).list();
        List<Map> userListOut=new ArrayList<Map>();
        for(int i=0;i<userList.size();i++){
            Map map=new HashMap();
            map.put("userId",userList.get(i).getId());
            map.put("userName",userList.get(i).getFirstName().trim()+userList.get(i).getLastName().trim());
            map.put("email",userList.get(i).getEmail());
            userListOut.add(map);
        }
        apiResult.setBody(userListOut);
        return apiResult;
    }

    //获取整组的成员id
    @RequestMapping(value="/getgroupbyuser", method = RequestMethod.GET)
    public ApiResult getGroupByUser(@RequestParam String userId)
    {
        ApiResult apiResult=new ApiResult();
        List<Group> groupList=identityService.createGroupQuery().groupMember(userId).list();
        apiResult.setBody(groupList);
        return apiResult;
    }

    /**
     * 获取用户组别信息
     * @param groupId
     * @return
     */
    @RequestMapping(value="/common/getuserbygroup", method = RequestMethod.GET)
    public ApiResult getUserByGroupId(@RequestParam String groupId)
    {
        ApiResult apiResult=new ApiResult();
        List<User> userList=identityService.createUserQuery().memberOfGroup(groupId).list();
        List<Map> userListOut=new ArrayList<Map>();
        for(int i=0;i<userList.size();i++){
            Map map=new HashMap();
            map.put("userId",userList.get(i).getId());
            map.put("userName",userList.get(i).getFirstName().trim()+userList.get(i).getLastName().trim());
            map.put("email",userList.get(i).getEmail());
            userListOut.add(map);
        }
        apiResult.setBody(userListOut);
        return apiResult;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="/common/getuserinfo", method = RequestMethod.GET)
    public ApiResult getUserInfo(@RequestParam String userId)
    {
        ApiResult apiResult=new ApiResult();
        User userInfo=identityService.createUserQuery().userId(userId).singleResult();
        Map map=new HashMap();
        map.put("userId",userInfo.getId());
        map.put("userName",userInfo.getFirstName().trim()+userInfo.getLastName().trim());
        map.put("email",userInfo.getEmail());
        apiResult.setBody(map);
        return apiResult;
    }

    /**
     * 根据名称获取用户信息
     */
    @RequestMapping(value="/common/getuserinfobyname", method = RequestMethod.GET)
    public ApiResult getUserInfoByName(@RequestParam String userName,String groupId)
    {
        ApiResult apiResult=new ApiResult();
        User userInfo=identityService.createUserQuery().memberOfGroup(groupId).userFirstName(userName).singleResult();
        Map map=new HashMap();
        map.put("userId",userInfo.getId());
        map.put("userName",userInfo.getFirstName().trim()+userInfo.getLastName().trim());
        map.put("email",userInfo.getEmail());
        apiResult.setBody(map);
        return apiResult;
    }

    /**
     * 根据名称模糊搜索
     * @param userName
     * @param groupId
     * @return
     */
    @RequestMapping(value="/common/getuserinfolikename", method = RequestMethod.GET)
    public ApiResult getUserInfoLikeName(@RequestParam String userName,String groupId)
    {
        ApiResult apiResult=new ApiResult();
        List<User> userList=identityService.createUserQuery().memberOfGroup(groupId).userFirstNameLike(userName).list();
        List userResult= new ArrayList<Map>();
        for(User userInfo:userList) {
            Map map = new HashMap();
            map.put("userId", userInfo.getId());
            map.put("userName", userInfo.getFirstName().trim() + userInfo.getLastName().trim());
            map.put("email", userInfo.getEmail());
            userResult.add(map);
        }
        apiResult.setBody(userList);
        return apiResult;
    }








}
