package com.nfjd.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-09-20.
 */
@Repository
public class ActivitiQueryDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     * 获取taskIdList的封装列表
     * @param taskIdListStr
     * @return
     */
    public List getTaskList (String taskIdListStr){
        if("".equals(taskIdListStr)) return new ArrayList<>();
        taskIdListStr = "(" + taskIdListStr + ")";
        String all="select * from VIEW_MIXTASK WHERE taskId in " + taskIdListStr + " ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all);
        return scpnPostList;
    }

    /**
     * 获取taskIdList的封装列表
     * @param taskIdListStr
     * @return
     */
    public List getTaskListNoVariables(String taskIdListStr){
        if("".equals(taskIdListStr)) return new ArrayList<>();
        taskIdListStr = "(" + taskIdListStr + ")";
        String all="select businessKey,taskId,name,assignee from VIEW_MIXTASK WHERE taskId in " + taskIdListStr + " GROUP BY taskId ORDER BY taskCreateTime DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all);
        return scpnPostList;
    }

    /**
     * 获取本人待办列表
     * @param userId
     * @param processDefinitionId
     * @return
     */
    /*public List queryAssignedTask (String userId , String processDefinitionId){
        String all="select * from VIEW_MIXTASK WHERE assignee = ? and processDefinitionId = ? ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all,userId ,processDefinitionId);
        return scpnPostList;
    }*/
    public List queryAssignedTask (String userId , String processDefinitionId ,String agentList){
        String all="select * from VIEW_MIXTASK WHERE processDefinitionId = ? and (assignee = ? or (executionId in (select ACT_RU_VARIABLE.EXECUTION_ID_ from ACT_RU_VARIABLE where NAME_ in (?) and TEXT_ = ?))) ORDER BY taskId DESC";
        List scpnPostList = jdbcTemplate.queryForList(all,processDefinitionId,userId,agentList,userId);
        return scpnPostList;
    }

    /**
     * 获取本人创建的
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryCreatedTask (String userId , String processDefinitionId){
        String all="select * from VIEW_MIXTASK WHERE variableValue = ? and variableName = 'createPerson' and processDefinitionId = ? ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all,userId ,processDefinitionId);
        return scpnPostList;
    }

    /**
     * 获取本人所有处理的（包括创建）
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryFinishTask (String userId , String processDefinitionId){
        //String all="select * from VIEW_MIXTASK WHERE processDefinitionId = ? and ((processInstanceId in (select ACT_HI_ACTINST.PROC_INST_ID_ from ACT_HI_ACTINST WHERE ASSIGNEE_ = ?)) or (variableValue = ? and variableName = 'createPerson') )  ORDER BY taskId DESC ";

        String condition1 = "((processInstanceId in (select ACT_HI_ACTINST.PROC_INST_ID_ from ACT_HI_ACTINST WHERE ASSIGNEE_ = ?)) or (variableValue = ? and variableName = 'createPerson') )";
        String processInstanceIdCondition1 = "(processInstanceId in (select ACT_HI_ACTINST.PROC_INST_ID_ from ACT_HI_ACTINST WHERE ASSIGNEE_ = ?))";
        String processInstanceIdCondition2 = "(processInstanceId in (select ACT_HI_VARINST.PROC_INST_ID_ from ACT_HI_VARINST WHERE variableValue = ? and variableName = 'belongPerson'))";
        String createCondition = "(variableValue = ? and variableName = 'createPerson')";
        String andCondition = "(" + processInstanceIdCondition1 + " or " + createCondition + " or "+ processInstanceIdCondition2 + ")";
        String nameNotLike = " name not like '%read%'";
        String all = "select * from VIEW_MIXTASK_HI WHERE processDefinitionId = ? and  " + andCondition + " and " + nameNotLike + " ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all,processDefinitionId,userId ,userId,userId);
        return scpnPostList;
    }

    /**
     * 判断单据是否与本人有关
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public int queryRelated (String userId , String processDefinitionId ,String orderId){
        //String all="select * from VIEW_MIXTASK WHERE processDefinitionId = ? and ((processInstanceId in (select ACT_HI_ACTINST.PROC_INST_ID_ from ACT_HI_ACTINST WHERE ASSIGNEE_ = ?)) or (variableValue = ? and variableName = 'createPerson') )  ORDER BY taskId DESC ";
        String allHI = "select * from VIEW_MIXTASK_HI WHERE processDefinitionId = ? and  variableValue = ? and businessKey = ? ORDER BY taskId DESC ";
        String allNow  = "select * from VIEW_MIXTASK WHERE processDefinitionId = ? and  variableValue = ? and businessKey = ? ORDER BY taskId DESC ";
        List scpnPostListHi = jdbcTemplate.queryForList(allHI,processDefinitionId,userId ,orderId);
        List scpnPostListNow = jdbcTemplate.queryForList(allNow,processDefinitionId,userId ,orderId);
        return scpnPostListHi.size()+scpnPostListNow.size();
    }

    /**
     * 获取本人已处理的
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public List queryHandledTask (String userId , String processDefinitionId){
        String all="select * from VIEW_MIXTASK WHERE processInstanceId in (select ACT_HI_ACTINST.PROC_INST_ID_ from ACT_HI_ACTINST WHERE ASSIGNEE_ = ?) and processDefinitionId = ? ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all,userId ,processDefinitionId);
        return scpnPostList;
    }

    /**
     * 本节点已处理工单
     * @param nodeName
     * @param processDefinitionId
     * @return
     */
    public List queryFinishedNode (String nodeName , String processDefinitionId){
        String all="select * from VIEW_MIXTASK_HI WHERE processInstanceId in (select ACT_HI_TASKINST.PROC_INST_ID_ from ACT_HI_TASKINST WHERE NAME_ = ?) and processDefinitionId = ? ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all,nodeName ,processDefinitionId);
        return scpnPostList;
    }


    /**
     * 所有
     * @param processDefinitionId
     * @return
     */
    public List queryAllTask ( String processDefinitionId){
        String all="select * from VIEW_MIXTASK WHERE  processDefinitionId = ? ORDER BY taskId DESC ";
        List scpnPostList = jdbcTemplate.queryForList(all,processDefinitionId);
        return scpnPostList;
    }

    /**
     * 将已签收工单退回组内
     * @param taskId
     */
    public void clearAssigned(String taskId){
        String all = "update ACT_RU_TASK set ASSIGNEE_ = null where ID_ = ?";
        jdbcTemplate.update(all,taskId);
    }




}
