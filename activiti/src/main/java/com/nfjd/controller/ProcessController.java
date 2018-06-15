package com.nfjd.controller;

import com.nfjd.config.ActivitiConfig;
import com.nfjd.pojo.ApiResult;
import com.nfjd.pojo.TaskInfo;
import com.nfjd.pojo.mypojo.MixTask;
import com.nfjd.util.DateTypeTran;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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





    @ApiOperation(value = "启动流程", notes = "请注意：在此项目中的部分信息会返还失败。。。", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "processDefinitionKey", value = "流程名称", required = true, dataType = "String")
    @ApiImplicitParams({ @ApiImplicitParam(dataType = "String", name = "orderId", value = "工单id", required = true) })
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 403, message = "权限不足") })
    @RequestMapping(value="/startprocess", method = RequestMethod.POST)
    public ApiResult startProcessInstance(@RequestParam String processDefinitionKey, String orderId,@RequestBody Map variables,@RequestParam String Tid){

        return null;
    }


    @RequestMapping(value="/startandclaim", method = RequestMethod.POST)
    public ApiResult startAndClaimProcessInstance(@RequestParam String processDefinitionKey, String orderId, String nextAssigner,String Tid,@RequestBody Map variables){

        return null;
    }

    /**
     * 签收任务
     * @param getMap
     */
    @RequestMapping(value="/claimtask", method = RequestMethod.POST)
    public void  claimTask(@RequestBody Map getMap,@RequestParam String Tid)
    {
    }



    //确认任务
    @RequestMapping(value="/completetask", method = RequestMethod.POST)
    public ApiResult<Map> completeTask(@RequestBody Map getMap,@RequestParam String Tid )
    {

        //apiResult.setBody(activitiMap);
        return null;
    }


    //签收和完成任务
    @RequestMapping(value="/claimandcompletetask", method = RequestMethod.POST)
    public void claimAndCompleteTask(@RequestBody Map getMap ,@RequestParam String Tid)
    {
    }

    //确认并指定下一环节接收人
    @RequestMapping(value="/completeandclaimtask", method = RequestMethod.POST)
    public ApiResult completeandclaimTask(@RequestBody Map getMap ,@RequestParam String Tid)
    {
        return null;
    }

    //查找taskId
    @RequestMapping(value="/getTaskIdByInsId", method = RequestMethod.POST)
    public ApiResult getTaskIdByInsId(@RequestBody Map getMap ,@RequestParam String Tid)
    {
        return null;
    }

    /**
     * 将已签收工单退回组内
     * @param taskId
     */
    @RequestMapping(value = "/clearAssigned", method = RequestMethod.GET)
    public void clearAssigned(@RequestParam String taskId){

    }




}
