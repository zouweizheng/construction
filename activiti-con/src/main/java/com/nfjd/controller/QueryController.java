package com.nfjd.controller;

import com.nfjd.config.ActivitiConfig;
import com.nfjd.mappers.MedalMixMapper;
import com.nfjd.pojo.ApiResult;
import com.nfjd.pojo.mypojo.MixTask;
import com.nfjd.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-03-15.
 */
@RestController
@RequestMapping(value="/query")
public class QueryController {

    private static final Logger log= LoggerFactory.getLogger(QueryController.class);

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


    /**
     * 查询本组待办工单
     * @param groupId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/querycandidatetask", method = RequestMethod.GET)
    public ApiResult queryCandidateTask(@RequestParam  String groupId,String processDefinitionId,String Tid)
    {

        List<Task> doingTasks=taskService.createTaskQuery().taskCandidateGroup(groupId).processDefinitionId(processDefinitionId).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskListNoVariables(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }

    /**
     * 查询本人本组待办工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/querymycandidatetask", method = RequestMethod.GET)
    public ApiResult queryMyCandidateTask(@RequestParam  String userId,String processDefinitionId,String Tid)
    {
        List<Group> groupList=identityService.createGroupQuery().groupMember(userId).list();
        List<String> groupIdList = new ArrayList<>();
        for(Group group : groupList){
            groupIdList.add(group.getId());
        }
        List<Task> doingTasks=taskService.createTaskQuery().taskCandidateGroupIn(groupIdList).processDefinitionId(processDefinitionId).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskListNoVariables(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }

    /**
     * 查询本人待办工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryassignedtask", method = RequestMethod.GET)
    public ApiResult queryAssignedTask(@RequestParam  String userId,String processDefinitionId,@RequestParam(value="agentList",required = false,defaultValue = "belongPerson")String agentList,String Tid)
    {
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryAssignedTask(userId, processDefinitionId,agentList));
        return apiResult;
    }

    /**
     * 根据待办id查询待办单详情
     * @param taskId
     * @return
     */
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
     * 查询本人提交及已处理的工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryfinishtask", method = RequestMethod.GET)
    public ApiResult queryFinishTask(@RequestParam  String userId,String processDefinitionId)
    {
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryFinishTask(userId,processDefinitionId));
        return apiResult;
    }

    /**
     * 判断单据是否与本人有关
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryRelated", method = RequestMethod.GET)
    public ApiResult queryRelated(@RequestParam  String userId,String processDefinitionId,String orderId)
    {
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryRelated(userId,processDefinitionId,orderId));
        return apiResult;
    }

    /**
     * 查询本人已处理的工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryHandledTask", method = RequestMethod.GET)
    public ApiResult queryHandledTask(@RequestParam  String userId,String processDefinitionId)
    {
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryHandledTask(userId,processDefinitionId));
        return apiResult;
    }
    /**
     * 查询本人创建的工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryCreatedTask", method = RequestMethod.GET)
    public ApiResult queryCreatedTask(@RequestParam  String userId,String processDefinitionId)
    {
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryCreatedTask(userId,processDefinitionId));
        return apiResult;
    }


    /**
     * 查询本人已处理工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryhistorytaskstate", method = RequestMethod.GET)
    public ApiResult queryHistoryTaskState(@RequestParam  String userId,String processDefinitionId,String Tid)
    {
        List<MixTask> orderIdList = medalMixMapper.queryHistoryTaskState(userId,processDefinitionId);
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(orderIdList);
        return apiResult;
    }


    /**
     * 查询本节点所有工单
     * @param nodeName
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/node/querytask", method = RequestMethod.GET)
    public ApiResult queryNodeTask(@RequestParam  String nodeName,String processDefinitionId,String Tid)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskName(nodeName).processDefinitionId(processDefinitionId).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }
    /**
     * 查询本节点已处理所有工单
     * @param nodeName
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/node/queryFinishNode", method = RequestMethod.GET)
    public ApiResult queryFinishNode(@RequestParam  String nodeName,String processDefinitionId)
    {
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryFinishNode(nodeName,processDefinitionId));
        return apiResult;
    }

    /**
     * 查询本节点本人待办工单
     * @param nodeName
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/node/queryassignedtask", method = RequestMethod.GET)
    public ApiResult queryNodeTask(@RequestParam  String nodeName,String processDefinitionId,String userId,String Tid)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskName(nodeName).taskAssignee(userId).processDefinitionId(processDefinitionId).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }

    /**
     * 查询本节点某张工单
     * @param nodeName
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/node/querysingletask", method = RequestMethod.GET)
    public ApiResult queryNodeSingleTask(@RequestParam  String nodeName,String processDefinitionId,String businessKey,String Tid)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskName(nodeName).processInstanceBusinessKey(businessKey).processDefinitionId(processDefinitionId).list();
        System.out.println("doingTasks"+doingTasks);
        ApiResult apiResult=new ApiResult();
        List taskList=activitiService.getTaskList(doingTasks);
        System.out.println("taskList:" + taskList);
        apiResult.setBody(taskList);
        return apiResult;
    }

    /**
     * 查询最近某张工单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/querysingletask", method = RequestMethod.GET)
    public ApiResult querySingleTask(@RequestParam String processDefinitionId,String businessKey,String Tid)
    {
        //Task dodfd = taskService.createTaskQuery().processDefinitionId(processDefinitionId).processInstanceBusinessKey(businessKey).processVariableValueLessThanOrEqual("belongPerson",businessKey);
        Task doingTask=taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionId(processDefinitionId).orderByTaskCreateTime().desc().singleResult();
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(doingTask);
        return apiResult;
    }


    /**
     * 查询所有工单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryalltask", method = RequestMethod.GET)
    public ApiResult queryAllTask(@RequestParam  String processDefinitionId,String Tid)
    {
        //List<HistoricActivityInstance> historicResult=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).list();
        /*List<Map> orderIdList=new ArrayList<Map>();
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
        return apiResult;*/
        ApiResult apiResult=new ApiResult();
        apiResult.setBody(activitiService.queryAllTask(processDefinitionId));
        return apiResult;
    }


    /**
     * 获取工单状态
     * @param taskId
     * @return
     */
    @RequestMapping(value="/getstatus", method = RequestMethod.GET)
    public ApiResult getStatus(@RequestParam String taskId,String userId,String Tid)
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
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (null == task) return apiResult;
        map = activitiService.getTaskResult(task);
        if(taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).count()>0){
            map.put("permit","yes");
        }
        else if(taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(agentPerson).processVariableValueEquals("belongPerson",userId).count()>0){
            map.put("permit","yes");
        }
        else if(taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(agentPerson).processVariableValueEquals("toreadPerson",userId).count()>0){
            map.put("permit","yes");
        }
        else{
            map.put("permit","no");
        }
        apiResult.setBody(map);
        return apiResult;
    }






}
