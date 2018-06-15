package com.forezp.controller;

import com.forezp.dao.ConfigInfoRepository;
import com.forezp.entity.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zou on 2018/1/16.
 */
@RestController
@RequestMapping(value = "/query")
public class QueryController {

    @Autowired
    private ConfigInfoRepository repository;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<ConfigInfo> QueryAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/findByEnName",method = RequestMethod.GET)
    public ConfigInfo QueryAll(@RequestParam String enName){
        return repository.findByEnName(enName);
    }

    @RequestMapping(value = "/findByEnNames",method = RequestMethod.GET)
    public ConfigInfo FindByEnNames(@RequestParam String enNames){
        String[] listEnName = enNames.split(",");
        ConfigInfo configInfo = new ConfigInfo();
        if(!(listEnName.length>0)) return configInfo;
        configInfo = repository.findByEnName(listEnName[0].toString().trim());
        for(int i=1 ; i<listEnName.length ; i++){
            if(null == configInfo) break;
            configInfo = getByEnName(configInfo.getConfigInfoList(),listEnName[i].trim().toString());
            if(null == configInfo) break;
        }
        return configInfo;
    }

    private ConfigInfo getByEnName(List<ConfigInfo> configInfoList , String enName){
        if(null == configInfoList) return null;
        if(!(configInfoList.size()>0)) return null;
        for(ConfigInfo configInfo : configInfoList){
            if(enName.equals(configInfo.getEnName())) return configInfo;
        }
        return null;
    }

}
