package com.nfjd.controller;

import com.nfjd.config.ActivitiConfig;
import com.nfjd.pojo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-03-15.
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

    private static final Logger log= LoggerFactory.getLogger(UserController.class);



    /**
     * 获取组别所有成员
     * @param groupId
     * @return
     */
    @RequestMapping(value="/getuserbygroup", method = RequestMethod.GET)
    public ApiResult getUserByGroup(@RequestParam String groupId)
    {
        return null;
    }

    /**
     * 获取个人所在的所有组别
     * @param userId
     * @return
     */
    @RequestMapping(value="/getgroupbyuser", method = RequestMethod.GET)
    public ApiResult getGroupByUser(@RequestParam String userId)
    {
        return null;
    }


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="/getuserinfobyid", method = RequestMethod.GET)
    public ApiResult getUserInfoById(@RequestParam String userId)
    {
        return null;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="/getuserinfo", method = RequestMethod.GET)
    public ApiResult getUserInfo(@RequestParam String userId)
    {
        return null;
    }

    /**
     * 根据名称获取用户信息
     */
    @RequestMapping(value="/getuserinfobyname", method = RequestMethod.GET)
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
    @RequestMapping(value="/getuserinfolikename", method = RequestMethod.GET)
    public ApiResult getUserInfoLikeName(@RequestParam String userName,String groupId)
    {
        return null;
    }
    @RequestMapping(value="/verifyUser", method = RequestMethod.GET)
    public ApiResult verifyUser(@RequestParam String userId,String password){
        return null;
    }

    @RequestMapping(value="/modifyUser", method = RequestMethod.GET)
    public void modifyUser(@RequestParam String userId,String password){

    }



}
