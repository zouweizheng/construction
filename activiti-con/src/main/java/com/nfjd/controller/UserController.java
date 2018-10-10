package com.nfjd.controller;

import com.nfjd.config.ActivitiConfig;
import com.nfjd.pojo.ApiResult;
import com.nfjd.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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

    @Autowired
    private ActivitiConfig activitiConfig;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private FormService formService;


    /**
     * 获取组别所有成员
     * @param groupId
     * @return
     */
    @RequestMapping(value="/getuserbygroup", method = RequestMethod.GET)
    public ApiResult getUserByGroup(@RequestParam String groupId)
    {
        ApiResult apiResult=new ApiResult();
        List<User> userList=identityService.createUserQuery().memberOfGroup(groupId).list();
        List<Map> userListOut=new ArrayList<Map>();
        for(int i=0;i<userList.size();i++){
            Map map=new HashMap();
            map.put("userId",userList.get(i).getId());
            map.put("userName",userList.get(i).getFirstName().trim()+userList.get(i).getLastName().trim());
            map.put("phone",userList.get(i).getEmail());
            userListOut.add(map);
        }
        apiResult.setBody(userListOut);
        return apiResult;
    }

    /**
     * 获取个人所在的所有组别
     * @param userId
     * @return
     */
    @RequestMapping(value="/getgroupbyuser", method = RequestMethod.GET)
    public ApiResult getGroupByUser(@RequestParam String userId)
    {
        ApiResult apiResult=new ApiResult();
        List<Group> groupList=identityService.createGroupQuery().groupMember(userId).list();
        apiResult.setBody(groupList);
        return apiResult;
    }


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="/getuserinfobyid", method = RequestMethod.GET)
    public ApiResult getUserInfoById(@RequestParam String userId)
    {
        ApiResult apiResult=new ApiResult();
        User userInfo=identityService.createUserQuery().userId(userId).singleResult();
        if(null==userInfo||"".equals(userId)) return apiResult;
        Map map=new HashMap();
        map.put("userId",userInfo.getId());
        map.put("userName",userInfo.getFirstName().trim()+userInfo.getLastName().trim());
        map.put("email",userInfo.getEmail());
        apiResult.setBody(map);
        return apiResult;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value="/getuserinfo", method = RequestMethod.GET)
    public ApiResult getUserInfo(@RequestParam String userId)
    {
        ApiResult apiResult=new ApiResult();
        User userInfo=identityService.createUserQuery().userId(userId).singleResult();
        Map map=new HashMap();
        map.put("userId",userInfo.getId());
        map.put("firstName",userInfo.getFirstName().trim());
        map.put("fullName",userInfo.getFirstName().trim()+userInfo.getLastName().trim());
        map.put("lastName",userInfo.getLastName().trim());
        map.put("email",userInfo.getEmail());
        List<Group> groupList=identityService.createGroupQuery().groupMember(userId).list();
        List<String> groupIdList = new ArrayList<>();
        for(Group group : groupList){
            groupIdList.add(group.getId());
        }
        map.put("groupIdList",groupIdList);
        apiResult.setBody(map);
        return apiResult;
    }

    /**
     * 根据名称获取用户信息
     */
    @RequestMapping(value="/getuserinfobyname", method = RequestMethod.GET)
    public ApiResult getUserInfoByName(@RequestParam String userName,String groupId)
    {
        ApiResult apiResult=new ApiResult();
        User userInfo=identityService.createUserQuery().memberOfGroup(groupId).userFirstName(userName).singleResult();
        Map map=new HashMap();
        map.put("userId",userInfo.getId());
        map.put("userName",userInfo.getFirstName().trim()+userInfo.getLastName().trim());
        map.put("email",userInfo.getEmail());
        apiResult.setBody(map);
        return apiResult;
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
        ApiResult apiResult=new ApiResult();
        List<User> userList=identityService.createUserQuery().memberOfGroup(groupId).userFirstNameLike(userName).list();
        List userResult= new ArrayList<Map>();
        for(User userInfo:userList) {
            Map map = new HashMap();
            map.put("userId", userInfo.getId());
            map.put("userName", userInfo.getFirstName().trim() + userInfo.getLastName().trim());
            map.put("email", userInfo.getEmail());
            userResult.add(map);
        }
        apiResult.setBody(userList);
        return apiResult;
    }
    @RequestMapping(value="/verifyUser", method = RequestMethod.GET)
    public ApiResult verifyUser(@RequestParam String userId,String password){
        ApiResult apiResult = new ApiResult();
        Map map = new HashMap();
        if(!identityService.checkPassword(userId,password)) {
            apiResult.setErrcode(10002);
            apiResult.setErrmsg("账号或密码错误");
        }
        return apiResult;
    }

    @RequestMapping(value="/modifyUser", method = RequestMethod.GET)
    public void modifyUser(@RequestParam String userId,String password){

    }



}
