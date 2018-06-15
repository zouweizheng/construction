package com.forezp.controller;

import com.forezp.dao.ConfigInfoRepository;
import com.forezp.entity.ConfigInfo;
import com.forezp.service.ConfigTestCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zou on 2018/1/16.
 */
@RestController
@RequestMapping(value = "/set")
public class SetConfigInfoController {

    @Autowired
    private ConfigInfoRepository repository;

    @Autowired
    ConfigTestCreate configTestCreate;

    @RequestMapping(value = "/insertConfigInfo",method = RequestMethod.GET)
    public void InsertConfigInfo(){
        /*repository.deleteAll();
        ConfigInfo configInfo1 = new ConfigInfo("guangdong","广东省",1);
        ConfigInfo configInfo11 = new ConfigInfo("guangzhou","广州市",1);
        ConfigInfo configInfo12 = new ConfigInfo("shenzhen","深圳市",2);
        ConfigInfo configInfo111 = new ConfigInfo("tianhe","天河区",1);
        ConfigInfo configInfo112 = new ConfigInfo("tianhe","海珠区",2);
        configInfo11.addConfigInfoList(configInfo111);
        configInfo11.addConfigInfoList(configInfo112);
        configInfo1.addConfigInfoList(configInfo11);
        configInfo1.addConfigInfoList(configInfo12);
        repository.save(configInfo1);*/
        ConfigInfo configInfo1 = new ConfigInfo("meetingRoom","会议室名称",1);
        ConfigInfo configInfo11 = new ConfigInfo("duogongneng","多功能会议室",1);
        /*configInfo11.setSecChName("3000");
        configInfo11.setSecEnName("单价（元/节）");*/
        ConfigInfo configInfo12 = new ConfigInfo("hiuyishi1","会议室1",2);
        ConfigInfo configInfo111 = new ConfigInfo("unitPrice","天河区",1);
        ConfigInfo configInfo112 = new ConfigInfo("tianhe","海珠区",2);
        configInfo11.addConfigInfoList(configInfo111);
        configInfo11.addConfigInfoList(configInfo112);
        configInfo1.addConfigInfoList(configInfo11);
        configInfo1.addConfigInfoList(configInfo12);
        repository.save(configInfo1);
    }


    @RequestMapping(value = "/insertConfigInfo1",method = RequestMethod.GET)
    public void InsertConfigInfo1(){

        repository.save(configTestCreate.FeeInfo());
    }

}
