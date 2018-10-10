package com.nfjd.service;

import com.nfjd.dao.ActivitiQueryDao;
import com.nfjd.mappers.MedalMixMapper;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZouWeizheng on 2017-03-30.
 */
@Service
public class ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    TaskService taskService;

    @Autowired
    MedalMixMapper medalMixMapper;

    @Autowired
    ActivitiQueryDao activitiQueryDao;

    public Map getTaskResult(Task task) {
        Map taskResult = new HashMap();
        taskResult.put("taskId", task.getId());
        taskResult.put("name", task.getName());
        taskResult.put("id", task.getId());
        taskResult.put("owner", task.getOwner());
        taskResult.put("assignee", task.getAssignee());
        taskResult.put("category", task.getCategory());
        taskResult.put("formkey", task.getFormKey());
        taskResult.put("processVariables", task.getProcessVariables());
        taskResult.put("taskLocalVariables", task.getTaskLocalVariables());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskCreateTime = formatter.format(task.getCreateTime());
        taskResult.put("taskCreateTime",taskCreateTime);
        return taskResult;
    }



    /*public List getHistoryTaskList(List<HistoricActivityInstance> historicResult) {
        List taskList = new ArrayList();
        for (HistoricActivityInstance historicActivityInstance : historicResult) {
            String processInstanceIdWait = historicActivityInstance.getProcessInstanceId();
            Map taskResult = activitiService.getVariables(processInstanceIdWait);
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceIdWait).singleResult();
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceIdWait).singleResult();
            if(null==task||"".equals(task)){
                taskResult.put("name", "end");
            }
            else{
                taskResult = getTaskResult(task);
                taskResult.put("variables", processInstance.getProcessVariables());
            }
            taskResult.put("processInstanceId", processInstanceIdWait);
            taskList.add(taskResult);
        }
        return taskList;
    }*/

    public Map getVariables(String processInstanceId)
    {
        Map map=new HashMap();
        Map variables=new HashMap();
        if(1>runtimeService.createExecutionQuery().processInstanceId(processInstanceId).count()) return map;
        if(runtimeService.hasVariable(processInstanceId,"orderId")) {
            String orderId = runtimeService.getVariable(processInstanceId, "orderId").toString();
            variables.put("orderId",orderId);
        }
        if(runtimeService.hasVariable(processInstanceId,"createPerson")) {
            String orderId = runtimeService.getVariable(processInstanceId, "createPerson").toString();
            variables.put("createPerson",orderId);
        }
        map.put("variables",variables);
        return map;
    }


    /**
     * 根据TaskList获取标准格式的MIXTASK
     * @param doingTasks
     * @return
     */
    public List getTaskList(List<Task> doingTasks) {
        String taskIdListStr = "";
        for (Task task : doingTasks) taskIdListStr += (task.getId().toString() + ",");
        if(taskIdListStr.length() > 0) taskIdListStr = taskIdListStr.substring(0,taskIdListStr.length()-1);
        List<Map> taskMixList = activitiQueryDao.getTaskList(taskIdListStr);
        return reviseTaskList(taskMixList);
    }

    public List getTaskListNoVariables(List<Task> doingTasks) {
        String taskIdListStr = "";
        for (Task task : doingTasks) taskIdListStr += (task.getId().toString() + ",");
        if(taskIdListStr.length() > 0) taskIdListStr = taskIdListStr.substring(0,taskIdListStr.length()-1);
        List<Map> taskMixList = activitiQueryDao.getTaskListNoVariables(taskIdListStr);
        return taskMixList;
    }

    /**
     * 查询本人待处理工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryAssignedTask(String userId,String processDefinitionId ,String agentList) {
        List<Map> taskMixList = activitiQueryDao.queryAssignedTask(userId,processDefinitionId,agentList);
        return reviseTaskList(taskMixList);
    }
    /**
     * 查询本人创建工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryCreatedTask (String userId , String processDefinitionId) {
        List<Map> taskMixList = activitiQueryDao.queryCreatedTask(userId,processDefinitionId);
        return reviseTaskList(taskMixList);
    }
    /**
     * 查询本人创建的及已处理的工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryFinishTask(String userId,String processDefinitionId) {
        List<Map> taskMixList = activitiQueryDao.queryFinishTask(userId,processDefinitionId);
        return reviseTaskList(taskMixList);
    }
    /**
     * 判断单据是否与本人有关
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public int queryRelated(String userId , String processDefinitionId ,String orderId) {
        return activitiQueryDao.queryRelated(userId,processDefinitionId,orderId);
    }
    /**
     * 查询本人已处理工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryHandledTask(String userId,String processDefinitionId) {
        List<Map> taskMixList = activitiQueryDao.queryHandledTask(userId,processDefinitionId);
        return reviseTaskList(taskMixList);
    }
    /**
     * 查询本节点已处理工单
     * @param nodeName
     * @param processDefinitionId
     * @return
     */
    public List queryFinishNode(String nodeName,String processDefinitionId) {
        List<Map> taskMixList = activitiQueryDao.queryFinishedNode(nodeName,processDefinitionId);
        return reviseTaskList(taskMixList);
    }

    /**
     * 查询所有工单
     * @param processDefinitionId
     * @return
     */
    public List queryAllTask(String processDefinitionId) {
        List<Map> taskMixList = activitiQueryDao.queryAllTask(processDefinitionId);
        return reviseTaskList(taskMixList);
    }
    /**
     * 将已签收工单退回组内
     * @param taskId
     */
    public void clearAssigned(String taskId){
        activitiQueryDao.clearAssigned(taskId);
    }

    /**
     * 将MIXTASK的列表封装成对象
     * @param taskMixList
     * @return
     */
    private List<Map> reviseTaskList(List<Map> taskMixList){
        if(!(taskMixList.size()>0)) return taskMixList;
        List<Map> returnList = new ArrayList<>();
        String taskId = taskMixList.get(0).get("taskId").toString().trim();
        Map singleResult = taskMixList.get(0);
        Map variables = new HashMap();
        for(Map map : taskMixList){
            String currentTaskId = map.get("taskId").toString().trim();
            if(taskId.equals(currentTaskId)) {
                variables.put(map.get("variableName").toString() ,map.get("variableValue").toString());
                singleResult = map;
            }
            else {
                taskId = currentTaskId;
                returnList.add(reviseTaskMap(singleResult , variables));
                variables.clear();
                variables.put(map.get("variableName").toString() ,map.get("variableValue").toString());
                singleResult = map;
            }
        }
        returnList.add(reviseTaskMap(singleResult , variables));
        return returnList;
    }

    private Map reviseTaskMap(Map singleResult ,Map variables){
        singleResult.remove("variableName");
        singleResult.remove("variableValue");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskCreateTime = formatter.format(singleResult.get("taskCreateTime"));
        singleResult.put("taskCreateTime",taskCreateTime);
        singleResult.put("variables",variables);
        return singleResult;
    }





}
