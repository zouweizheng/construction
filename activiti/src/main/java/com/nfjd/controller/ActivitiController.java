package com.nfjd.controller;

import com.alibaba.fastjson.JSONObject;
import com.nfjd.config.ActivitiConfig;
import com.nfjd.pojo.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


    /**
     * 启动流程
     * @param processKey
     * @param orderId
     * @param userId
     * @return
     */
    @RequestMapping(value="/startprocess", method = RequestMethod.GET)
    public ApiResult startProcessInstance( @RequestParam String processKey, String orderId, String userId){

        return null;
    }

    @RequestMapping(value="/startprocessnew", method = RequestMethod.POST)
    public ApiResult startProcessInstanceNew(@RequestParam String processKey, String orderId,@RequestBody Map variables){

        return null;
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

        return null;
    }

    @RequestMapping(value="/startandclaimnew", method = RequestMethod.POST)
    public ApiResult startAndClaimProcessInstanceNew(@RequestParam String processKey, String orderId, String nextAssigner,@RequestBody Map variables){

        return null;
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
        return null;
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
        return null;
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
        return null;
    }


    @RequestMapping(value="/querytask",method = RequestMethod.GET)
    public ApiResult queryTask(@RequestParam String taskId)
    {
        return null;
    }

    @RequestMapping(value="/queryinstance",method = RequestMethod.GET)
    public ApiResult queryInstance(@RequestParam String instanceId)
    {
        return null;
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
        return null;

    }


    @RequestMapping(value="/queryfinishtasknew", method = RequestMethod.GET)
    public ApiResult queryFinishTaskNew(@RequestParam  String userId,String processDefinitionId)
    {
        return null;

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
        return null;
    }

    /**
     * 查询所有工单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryalltask", method = RequestMethod.GET)
    public ApiResult queryAllTask(@RequestParam  String processDefinitionId)
    {
        return null;
    }

    /**
     * 获取工单状态
     * @param processInstanceId
     * @return
     */
    @RequestMapping(value="/getstatus", method = RequestMethod.GET)
    public ApiResult getStatus(@RequestParam String processInstanceId,String userId)
    {
        return null;
    }


    /**
     * 签收任务
     * @param getMap
     */
    @RequestMapping(value="/claimtask", method = RequestMethod.POST)
    public void  claimTask(@RequestBody Map getMap)
    {
    }


    //确认任务
    @RequestMapping(value="/completetask", method = RequestMethod.POST)
    public ApiResult completeTask(@RequestBody Map getMap )
    {
        return null;
    }


    //签收和完成任务
    @RequestMapping(value="/claimandcompletetask", method = RequestMethod.POST)
    public void claimAndCompleteTask(@RequestBody Map getMap )
    {

    }

    //确认并指定下一环节接收人
    @RequestMapping(value="/completeandclaimtask", method = RequestMethod.POST)
    public ApiResult completeandclaimTask(@RequestBody Map getMap)
    {
        return null;
    }



    //获取整组的成员id
    @RequestMapping(value="/getuserbygroup", method = RequestMethod.GET)
    public ApiResult getUserByGroup(@RequestParam String groupId)
    {
        return null;
    }

    //获取整组的成员id
    @RequestMapping(value="/getgroupbyuser", method = RequestMethod.GET)
    public ApiResult getGroupByUser(@RequestParam String userId)
    {
        return null;
    }

    /**
     * 获取用户组别信息
     * @param groupId
     * @return
     */
    @RequestMapping(value="/common/getuserbygroup", method = RequestMethod.GET)
    public ApiResult getUserByGroupId(@RequestParam String groupId)
    {
        return null;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="/common/getuserinfo", method = RequestMethod.GET)
    public ApiResult getUserInfo(@RequestParam String userId)
    {
        return null;
    }

    /**
     * 根据名称获取用户信息
     */
    @RequestMapping(value="/common/getuserinfobyname", method = RequestMethod.GET)
    public ApiResult getUserInfoByName(@RequestParam String userName,String groupId)
    {
        return null;
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
        return null;
    }








}
