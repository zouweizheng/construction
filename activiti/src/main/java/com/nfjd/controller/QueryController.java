package com.nfjd.controller;

import com.nfjd.config.ActivitiConfig;
import com.nfjd.pojo.ApiResult;
import com.nfjd.pojo.mypojo.MixTask;
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



    /**
     * 查询本组待办工单
     * @param groupId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/querycandidatetask", method = RequestMethod.GET)
    public ApiResult queryCandidateTask(@RequestParam  String groupId,String processDefinitionId,String Tid)
    {
        return null;
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
        return null;
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
        return null;
    }

    /**
     * 根据待办id查询待办单详情
     * @param taskId
     * @return
     */
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
     * 查询本人提交及已处理的工单
     * @param userId
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryfinishtask", method = RequestMethod.GET)
    public ApiResult queryFinishTask(@RequestParam  String userId,String processDefinitionId)
    {
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
    }

    /**
     * 查询最近某张工单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/querysingletask", method = RequestMethod.GET)
    public ApiResult querySingleTask(@RequestParam String processDefinitionId,String businessKey,String Tid)
    {
        return null;
    }


    /**
     * 查询所有工单
     * @param processDefinitionId
     * @return
     */
    @RequestMapping(value="/queryalltask", method = RequestMethod.GET)
    public ApiResult queryAllTask(@RequestParam  String processDefinitionId,String Tid)
    {
        return null;
    }


    /**
     * 获取工单状态
     * @param taskId
     * @return
     */
    @RequestMapping(value="/getstatus", method = RequestMethod.GET)
    public ApiResult getStatus(@RequestParam String taskId,String userId,String Tid)
    {
        return null;
    }






}
