package com.forezp.controller;

import com.forezp.dao.VersionInfoRepository;
import com.forezp.entity.VersionInfo;
import com.forezp.service.CurrentVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by zou on 2018/1/16.
 */
@RestController
@RequestMapping(value = "/version")
public class VersionInfoController {

    @Autowired
    private VersionInfoRepository repository;

    @Autowired
    CurrentVersion currentVersion;

    @RequestMapping(value = "/setVersionInfo",method = RequestMethod.POST)
    public void InsertConfigInfo(@RequestBody VersionInfo version){

        version.setCreateTime(new Date());
        repository.save(version);
    }


    @RequestMapping(value = "/getVersionInfo",method = RequestMethod.GET)
    public VersionInfo getVersionInfo(@RequestParam String version){

        return repository.findByVersionOrderByCreateTimeDesc(version);
    }

    @RequestMapping(value = "/getNewsVersionInfo",method = RequestMethod.GET)
    public VersionInfo getNewsVersionInfo(){

        List<VersionInfo> versionInfos = repository.findByPojoInfoOrderByCreateTimeDesc("VersionInfo");
        if(null == versionInfos || 0==versionInfos.size()) return null;
        return versionInfos.get(0);

        /*VersionInfo versionInfo = new VersionInfo();
        versionInfo.setVersion("con1.1.1");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("buyer", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("phone",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<VersionInfo> example = Example.of(versionInfo,matcher);
        return repository.findOne(example);*/
    }

}
