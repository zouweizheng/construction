package com.nfjd.service;

import com.nfjd.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {


    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ActivitiService activitiService;


    @Autowired
    RepositoryService repositoryService;


    public List<Map> queryCandidateTask(String groupId, String processDefinitionKey)
    {
        List<Task> doingTasks = taskService.createTaskQuery().taskCandidateGroup(groupId).processDefinitionKey(processDefinitionKey).list();
        return activitiService.getTaskListNoVariables(doingTasks);
    }

    public List<Map> queryCandidateTask(List<String> groupIds, String processDefinitionKey)
    {
        List<Task> doingTasks = taskService.createTaskQuery().taskCandidateGroupIn(groupIds).processDefinitionKey(processDefinitionKey).list();
        return activitiService.getTaskListNoVariables(doingTasks);
    }

    public List<Map> queryCandidateTask(List<String> groupIds, List<String> processDefinitionKeys)
    {
        List<Task> doingTasks = taskService.createTaskQuery().taskCandidateGroupIn(groupIds).processDefinitionKeyIn(processDefinitionKeys).list();
        return activitiService.getTaskListNoVariables(doingTasks);
    }

    public List<Map> queryMyCandidateTask(String userId, String processDefinitionKey)
    {
        List<Group> groupList=identityService.createGroupQuery().groupMember(userId).list();
        List<String> groupIdList = new ArrayList<>();
        for(Group group : groupList){
            groupIdList.add(group.getId());
        }
        return queryCandidateTask(groupIdList,processDefinitionKey);
    }

    public List<Map> queryMyCandidateTask(String userId, List<String> processDefinitionKeys)
    {
        List<Group> groupList=identityService.createGroupQuery().groupMember(userId).list();
        List<String> groupIdList = new ArrayList<>();
        for(Group group : groupList){
            groupIdList.add(group.getId());
        }
        return queryCandidateTask(groupIdList,processDefinitionKeys);
    }



    public List<HistoricProcessInstance> queryCreateTask(String userId, String processDefinitionKey)
    {
        List processDefinitionIds = new ArrayList();
        processDefinitionIds.add(repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult().getId());
        List<HistoricProcessInstance> processInstanceList = historyService.createHistoricProcessInstanceQuery().startedBy(userId).list();
        return processInstanceList;
    }

    public List<HistoricProcessInstance> queryCreateTask(String userId,List<String> processDefinitionKeys)
    {
        List processDefinitionIds = new ArrayList();
        for(String processDefinitionKey:processDefinitionKeys){
            processDefinitionIds.add(repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult().getId());
        }
        List<HistoricProcessInstance> processInstanceList = historyService.createHistoricProcessInstanceQuery().startedBy(userId).list();
        return processInstanceList;
    }

    public Task queryTask(String taskId)
    {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    public List<Map> queryNodeTask(String nodeName,String processDefinitionKey)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskName(nodeName).processDefinitionKey(processDefinitionKey).list();
        return activitiService.getTaskList(doingTasks);
    }
    public List<Map> queryNodeTask(String nodeName,List<String> processDefinitionKeys)
    {
        List taskList = new ArrayList();
        for(String processDefinitionKey:processDefinitionKeys){
            taskList.addAll(queryNodeTask(nodeName,processDefinitionKey));
        }
        return taskList;
    }


    public List<Task> queryMyNodeTask(String nodeName,String processDefinitionKey,String userId)
    {
        List<Task> doingTasks=taskService.createTaskQuery().taskName(nodeName).taskAssignee(userId).processDefinitionKey(processDefinitionKey).list();
        return activitiService.getTaskList(doingTasks);
    }

    public List<Task> queryMyNodeTask(String nodeName,List<String> processDefinitionKeys,String userId)
    {
        List taskList = new ArrayList();
        for(String processDefinitionKey:processDefinitionKeys){
            taskList.addAll(queryMyNodeTask(nodeName,processDefinitionKey,userId));
        }
        return taskList;
    }

    public List queryAllTask(String processDefinitionKey)
    {
        List processDefinitionIds = new ArrayList();
        processDefinitionIds.add(repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult().getId());
        return activitiService.queryAllTask(processDefinitionKey);
    }

    public List queryAllTask(List<String> processDefinitionKeys)
    {
        List alltaskList = new ArrayList();
        for(String processDefinitionKey:processDefinitionKeys){
            alltaskList.addAll(queryAllTask(processDefinitionKey));
        }
        return alltaskList;
    }

    public List<String> getPermitStatus(String taskId, String userId, List<String> groupIds)
    {
        List<String> permitStatus = new ArrayList<>();
        if(null!=userId&&!"".equals(userId)){
            if(taskService.createTaskQuery().taskId(taskId).taskAssignee(userId).count()>0){
                permitStatus.add("assignee");
            }
            if(taskService.createTaskQuery().taskId(taskId).taskCandidateGroupIn(groupIds).or().taskCandidateUser(userId).count()>0){
                permitStatus.add("candidate");
            }
        }
        if(null!=groupIds&&groupIds.size()>0){
            for(String groupId : groupIds){
                if(taskService.createTaskQuery().taskId(taskId).taskCandidateUser(groupId).count()>0){
                    if(permitStatus.contains("candidate")) break;
                    permitStatus.add("candidate");
                }
            }
        }
        return permitStatus;
    }

    public List queryFinishNode(String nodeName, String processDefinitionKey)
    {
        return activitiService.queryFinishNode(nodeName,repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult().getId());
    }


}
