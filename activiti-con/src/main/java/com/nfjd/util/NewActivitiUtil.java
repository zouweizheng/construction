package com.nfjd.util;

import com.alibaba.fastjson.JSONObject;
import com.nfjd.pojo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-02-22.
 */
public class NewActivitiUtil {

    private static final Logger log = LoggerFactory.getLogger(NewActivitiUtil.class);


    /**
     * 启动流程
     */
    public static ApiResult startProcessInstance(String url, String processDefinitionKey, String orderId, String userId, String Tid){

        //利用jsonobject传输
        Map<String,Object> variables=new HashMap<String, Object>();
        variables.put("orderId",orderId);
        variables.put("createPerson",userId);
        variables.put("belongPerson",userId);
        log.info(variables.toString());
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/process/startprocess?processDefinitionKey="+processDefinitionKey+"&orderId="+orderId+"&Tid="+Tid,ApiResult.class);
        //return  Result;
        return postData( variables,url+"/process/startprocess?processDefinitionKey="+processDefinitionKey+"&userId="+userId+"&orderId="+orderId+"&Tid="+Tid);
    }

    public static ApiResult startProcessNew(String url, String processKey, String orderId, Map variables, String Tid){

        return postData( variables,url+"/process/startprocessnew?processDefinitionKey="+processKey+"&orderId="+orderId);
    }

    /**
     * 启动并签收
     * @param url
     * @param processKey
     * @param orderId
     * @param userId
     * @param nextAssigner
     * @return
     */
    public static ApiResult startAndClaim(String url, String processKey, String orderId, String userId, String nextAssigner, String Tid){

        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/activiti/startandclaim?processKey="+processKey+"&userId="+userId+"&orderId="+orderId+"&nextAssigner="+nextAssigner+"&Tid="+Tid,ApiResult.class);
        return  Result;
        //return postData( parameters,url+"/activiti/startprocess");
    }

    public static ApiResult startAndClaimNew(String url, String processKey, String orderId, String nextAssigner, Map variables, String Tid){

        return postData( variables,url+"/process/startandclaimnew?processKey="+processKey+"&orderId="+orderId+"&nextAssigner="+nextAssigner+"&Tid="+Tid);
    }


    /**
     * 获取组别待处理工单
     * @param url
     * @param groupId
     * @param processDefinitionKey
     * @return
     */
    public static ApiResult<List<Map>> queryCandidateTask(String url, String groupId, String processDefinitionKey, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/querycandidatetask?groupId="+groupId+"&processDefinitionKey="+processDefinitionKey+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }


    /**
     * 获取流程状态
     * @param url
     * @param processInstanceId
     * @return
     */
    public static ApiResult<Map> getStatus(String url, String processInstanceId, String userId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<Map> Result = template.getForObject(url+"/query/getstatus?processInstanceId="+processInstanceId+"&userId="+userId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    /**
     * 查询本人待办工单
     * @param url
     * @param userId
     * @param processDefinitionKey
     * @return
     */
    public static ApiResult<List<Map>> queryAssignedTask(String url, String userId, String processDefinitionKey, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/queryassignedtask?userId="+userId+"&processDefinitionKey="+processDefinitionKey+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    public static ApiResult<List<Map>> queryAssignedTask(String url, String userId, String agentPerson, String processDefinitionKey, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/queryassignedtasknew?userId="+userId+"&agentPerson="+agentPerson+"&processDefinitionKey="+processDefinitionKey+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    /**
     * 查找本节点工单
     * @param url
     * @param nodeName
     * @param processDefinitionKey
     * @return
     */
    public static ApiResult<List<Map>> queryNodeTask(String url, String nodeName, String processDefinitionKey, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/querynodetask?nodeName="+nodeName+"&processDefinitionKey="+processDefinitionKey+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    /**
     * 查找所有工单
     * @param url
     * @param processDefinitionId
     * @return
     */
    public static ApiResult<List<Map>> queryAllTask(String url, String processDefinitionId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/queryalltask?processDefinitionId="+processDefinitionId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }


    /**
     * 查找本人已完成工单
     * @param url
     * @param userId
     * @param processDefinitionId
     * @return
     */
    public static ApiResult<List<Map>> queryFinishTask(String url, String userId, String processDefinitionId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/queryfinishtask?userId="+userId+"&processDefinitionId="+processDefinitionId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    public static ApiResult<List<Map>> queryFinishTaskNew(String url, String userId, String processDefinitionId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/query/queryfinishtasknew?userId="+userId+"&processDefinitionId="+processDefinitionId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    /**
     * 签收任务
     * @param url
     * @param taskId
     * @param userId
     * @return
     */
    public static ApiResult claimTask(String url, String taskId, String userId, String Tid)
    {
        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        parameters.put("taskId",taskId);
        parameters.put("userId",userId);
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/process/claimtask?Tid="+Tid);
    }

    /**
     * 确认工单
     * @param url
     * @param taskId
     * @param userId
     * @param variables
     * @return
     */
    public static ApiResult completeTask(String url, String taskId, String userId, Map variables, String Tid)
    {
        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        parameters.put("taskId",taskId);
        parameters.put("userId",userId);
        parameters.put("variables",variables);
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/process/completetask?Tid="+Tid);
    }

    /**
     * 签收和确认工单
     * @param url
     * @param taskId
     * @param userId
     * @param variables
     * @return
     */
    public static ApiResult claimAndCompleteTask(String url, String taskId, String userId, Map variables, String Tid)
    {
        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        //parameters.put("processInstanceId",processInstanceId);
        parameters.put("taskId",taskId);
        parameters.put("userId",userId);
        parameters.put("variables",variables);
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/process/claimandcompletetask?Tid="+Tid);
    }

    /**
     * 完成并指定下一环节的接收人
     * @param url
     * @param processInstanceId
     * @param userId
     * @param nextAssigner
     * @param variables
     * @return
     */
    public static ApiResult completeandclaimTask(String url, String processInstanceId, String userId, String nextAssigner, Map variables, String Tid)
    {
        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        parameters.put("processInstanceId",processInstanceId);
        parameters.put("nextAssigner",nextAssigner);
        parameters.put("userId",userId);
        parameters.put("variables",variables);
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/process/completeandclaimtask?Tid="+Tid);
    }


    /**
     *获取整组的成员id
     * @param url
     * @param groupId
     * @return
     */
    public static ApiResult<List<Map>> getUserByGroup(String url, String groupId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/user/getuserbygroup?groupId="+groupId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }


    /**
     * 获取整组的成员id
     * @param url
     * @param userId
     * @return
     */
    public static ApiResult<List<Map>> getGroupByUser(String url, String userId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<List<Map>> Result = template.getForObject(url+"/user/getgroupbyuser?userId="+userId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }


    public static ApiResult<Map> getUserInfoByName(String url, String userName, String groupId, String Tid)
    {
        RestTemplate template = new RestTemplate();
        ApiResult<Map> Result = template.getForObject(url+"/user/getuserinfobyname?userName="+userName+"&groupId="+groupId+"&Tid="+Tid,ApiResult.class);
        return  Result;
    }

    /**
     * 用于post推送的工具
     * @param postData
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static String postData(JSONObject postData, String url, String userName, String password)
    {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject medalRule= postData;
        HttpEntity<String> formEntity =new HttpEntity<String>(medalRule.toJSONString(), headers);
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(
                new BasicAuthorizationInterceptor(userName, password));
        String result =template.postForObject(url, formEntity, String.class);
        return result;
    }

    public static ApiResult postData(JSONObject postData, String url)
    {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject medalRule= postData;
        HttpEntity<String> formEntity =new HttpEntity<String>(medalRule.toJSONString(), headers);
        RestTemplate template = new RestTemplate();
        ApiResult result =template.postForObject(url, formEntity, ApiResult.class);
        return result;
    }

    public static ApiResult postData(Map postData, String url)
    {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject medalRule= new JSONObject();
        medalRule= DateTypeTran.MapToJSONObject(postData);
        HttpEntity<String> formEntity =new HttpEntity<String>(medalRule.toString(), headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResult> result =template.postForEntity(url, formEntity, ApiResult.class);
        ApiResult apiResult=result.getBody();
        return apiResult;
    }

}
