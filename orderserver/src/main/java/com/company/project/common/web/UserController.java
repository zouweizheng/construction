package com.company.project.common.web;

import com.company.project.construction.pojo.ConstructionConfigInfo;
import com.company.project.core.config.MyConfig;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.util.NewActivitiUtil;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZouWeizheng on 2017-12-12.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    ConstructionConfigInfo constructionConfigInfo;

    @RequestMapping("/getUserInfo")
    public Result getUserInfo(@RequestAttribute String userId,@RequestAttribute String tid){
        ApiResult apiResult = NewActivitiUtil.getUserInfo(constructionConfigInfo.getActivitiUrl(),userId,tid);
        if(0==apiResult.getErrcode()){
            return ResultGenerator.genSuccessResult(apiResult.getBody());
        }
        else return ResultGenerator.genFailResult("activiti返回错误");
    }

    @RequestMapping("/getUserByGroupId")
    public Result getUserByGroupId(@RequestParam String groupId, @RequestAttribute String tid){
        ApiResult apiResult = NewActivitiUtil.getUserByGroup(constructionConfigInfo.getActivitiUrl(),groupId,tid);
        if(0==apiResult.getErrcode()){
            return ResultGenerator.genSuccessResult(apiResult.getBody());
        }
        else return ResultGenerator.genFailResult("activiti返回错误");
    }
}
