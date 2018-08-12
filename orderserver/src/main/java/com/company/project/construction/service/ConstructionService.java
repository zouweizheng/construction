package com.company.project.construction.service;


import com.company.project.common.pojo.ConfigInfo;
import com.company.project.construction.mapper.ConOrderExtMapper;
import com.company.project.construction.mapper.ConstructionMapper;
import com.company.project.construction.pojo.ConstructionConfigInfo;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.service.AbstractService;
import com.company.project.core.util.NewActivitiUtil;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.dao.ConOrderMapper;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.service.ConOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/10/31.
 */
@Service("ConstructionService")
public class ConstructionService extends AbstractService<ConstructionPojo,ConstructionMapper>{

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ConOrderService conOrderService;

    @Autowired
    ConstructionConfigInfo constructionConfigInfo;


    /*public List<ConOrder> findConOrder(String projectName, String motorcadeId, String motorcadeName, String workType, String createPerson, String startTime, String endTime){
        ConOrder conOrder = new ConOrder();
        Condition condition = new Condition(conOrder.getClass());
        condition.and().andEqualTo("",)
        List<ConOrder> conOrderService.findByCondition()
    }

    public Map getTotalInfo(List<ConOrder> conOrders){
        for
    }*/

    /**
     * 判断是否为重复单
     * @param constructionPojo
     * @return
     */
    public boolean isRepeatOrder(ConstructionPojo constructionPojo,int repeatTime){
        try {
            ConOrder conOrder = constructionPojo.getConOrder();
            String motorcadeId = conOrder.getMotorcadeId();
            String workType = conOrder.getWorkType();
            Date nowDate = new Date();
            conOrder.setCreateTime(nowDate);
            constructionPojo.setConOrder(conOrder);
            Calendar c = Calendar.getInstance();
            c.setTime(nowDate);
            c.add(Calendar.SECOND, 0-repeatTime);
            Date date = c.getTime();
            if(null==motorcadeId||"".equals(motorcadeId)) return true;
            if(null==workType||"".equals(workType)) return true;
            Condition condition = new Condition(conOrder.getClass());
            condition.and().andEqualTo("motorcadeId",motorcadeId).andEqualTo("workType",workType).andGreaterThanOrEqualTo("createTime",date);
            List<ConOrder> conOrderList = conOrderService.findByCondition(condition);
            if(null == conOrderList || conOrderList.size()==0) return false;
            else return true;
        }catch (Exception e){
            return true;
        }

    }

    /**
     * 获取配置信息
     * @param configKey
     */
    @Cacheable(value = "ConfigInfo", key = "#configKey")
    public ConfigInfo getConfig(String configKey){
        System.out.print("没有命中缓存！");
        ApiResult<Map> apiResult = NewActivitiUtil.getUserInfo(constructionConfigInfo.getActivitiUrl(),configKey,"");
        if(0==apiResult.getErrcode()){
            Map body = apiResult.getBody();
            Object configValueObj = body.get("email");
            if(null==configValueObj||"".equals(configValueObj)) return null;
            ConfigInfo configInfo = new ConfigInfo();
            configInfo.setConfigKey(configKey);
            configInfo.setConfigValue(configValueObj.toString());
            return configInfo;
        }
        else return null;

    }




}
