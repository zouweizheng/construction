package com.nfjd.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-02-22.
 */
public class ActivitiUtil {

    private static final Logger log = LoggerFactory.getLogger(ActivitiUtil.class);

    /**
     * 获取activiti的版本信息
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static JSONObject getProperties(String url,String userName,String password){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(
                new BasicAuthorizationInterceptor(userName, password));
        JSONObject Result = template.getForObject(url+"/service/management/properties",JSONObject.class);
        return  Result;
    }

    /**
     * 启动流程
     * @param processId
     * @param orderId
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static String startProcessInstance(String processId, String orderId, String url, String userName, String password){

        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        JSONObject variables=new JSONObject();
        variables.put("createPerson",userName);
        variables.put("orderId",orderId);
        parameters.put("businessKey",orderId);
        parameters.put("processDefinitionId",processId);
        //parameters.put("variables",variables);
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/service/runtime/process-instances",userName,password);
    }

    /**
     * 根据id获取流程的参数
     * @param processInstanceId
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static JSONObject getVariables(String processInstanceId, String url, String userName, String password)
    {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(
                new BasicAuthorizationInterceptor(userName, password));
        JSONObject Result = template.getForObject(url+"/service/runtime/process-instances/"+processInstanceId,JSONObject.class);
        return  Result;
    }

    /**
     * 查询组别待处理工单
     * @param groupId
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static JSONObject queryCandidateTask(String groupId, String url, String userName, String password)
    {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(
                new BasicAuthorizationInterceptor(userName, password));
        JSONObject Result = template.getForObject(url+"/service/runtime/tasks?candidateGroup="+groupId,JSONObject.class);
        return  Result;
    }

    /**
     * 查询组别待处理工单
     * @param userId
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static JSONObject queryAssignedTask(String userId, String url, String userName, String password)
    {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(
                new BasicAuthorizationInterceptor(userName, password));
        JSONObject Result = template.getForObject(url+"/service/runtime/tasks?assignee="+userId,JSONObject.class);
        return  Result;
    }

    /**
     * 签收任务
     * @param userId
     * @param taskId
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static String claimTask(String userId, String taskId, String url, String userName, String password)
    {
        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        parameters.put("action","claim");
        parameters.put("assignee",userId);
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/service/runtime/tasks/"+taskId,userName,password);
    }

    /**
     * 确认任务
     * @param taskId
     * @param variables
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static String completeTask(String taskId, List<Map<String,String>> variables, String url, String userName, String password)
    {
        //利用jsonobject传输
        JSONObject parameters=new JSONObject();
        parameters.put("action","complete");
        if(variables!=null)
        {
            parameters.put("variables",variables);
        }
        log.info(parameters.toJSONString());
        return postData( parameters,url+"/service/runtime/tasks/"+taskId,userName,password);
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

}
