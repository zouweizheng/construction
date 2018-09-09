package com.company.project.construction.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.company.project.common.pojo.ConfigInfo;
import com.company.project.construction.mapper.ConstructionMapper;
import com.company.project.construction.pojo.ConstructionConfigInfo;
import com.company.project.construction.pojo.ConstructionPojo;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.service.AbstractService;
import com.company.project.core.util.NewActivitiUtil;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import com.company.project.foundation.model.ConOrder;
import com.company.project.foundation.model.ConOrderDel;
import com.company.project.foundation.model.ConOrderDelRecord;
import com.company.project.foundation.service.ConOrderDelRecordService;
import com.company.project.foundation.service.ConOrderDelService;
import com.company.project.foundation.service.ConOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Condition;

import java.util.*;


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

    @Autowired
    ConOrderDelService conOrderDelService;

    @Autowired
    ConOrderDelRecordService conOrderDelRecordService;


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


    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCon(String userId,Integer id, String deleteReason) throws Exception {
        try {
            ConOrder conOrder = conOrderService.findById(id);
            if (null == conOrder || "".equals(conOrder)) return false;
            String conOrderStr = JSONObject.toJSONString(conOrder);
            ConOrderDel conOrderDel = JSON.parseObject(conOrderStr, new TypeReference<ConOrderDel>() {
            });
            ConOrderDelRecord conOrderDelRecord = JSON.parseObject(conOrderStr, new TypeReference<ConOrderDelRecord>() {
            });
            conOrderDelRecord.setId(null);
            conOrderDelRecord.setDeletePerson(userId);
            conOrderDelRecord.setDeleteReason(deleteReason);
            conOrderDelRecord.setDeleteTime(new Date());
            conOrderDelRecord.setDealType("删除");
            conOrderDelRecordService.save(conOrderDelRecord);
            conOrderDelService.save(conOrderDel);
            conOrderService.deleteById(id);
        }catch (Exception e){
            throw e;
        }
        return true;
    }

    public Map getAnalysisTab(String userId){
        Map returnMap = new HashMap();
        String activitiUrl = constructionConfigInfo.getActivitiUrl();
        String authType = "createAuth";
        //是否具有projectAuth权限
        ApiResult<Map> apiResult = NewActivitiUtil.getUserInfo(activitiUrl,userId,"");
        if (!(0==apiResult.getErrcode())) {
            return returnMap;
        }
        Map userInfo =  apiResult.getBody();
        String userName = userInfo.get("firstName").toString();
        List<String> projectsOfMine = getProjectsByUserId(userId).get("projectsOfMine");
        List<String> projectsOfAll = getProjectsByUserId(userId).get("projectsOfAll");
        if(!(null==projectsOfMine||projectsOfMine.size()==0)){
            authType = "projectAuth";
        }
        //是否具有analysisAuth权限
        ApiResult<List<Map>> listApiResult = NewActivitiUtil.getGroupByUser(activitiUrl,userId,"");
        if (0==listApiResult.getErrcode()) {
            List<Map> groupList = listApiResult.getBody();
            for(Map map: groupList){
                if(constructionConfigInfo.getAnalysisGroup().equals(map.get("id"))){
                    authType = "analysisAuth";
                    break;
                }
            }
        }

        returnMap.put("auth",authType);
        if("createAuth".equals(authType)){
            returnMap.put("projectName",projectsOfAll);
            returnMap.put("createPerson",userName);
        }
        if("projectAuth".equals(authType)){
            returnMap.put("projectName",projectsOfMine);
            returnMap.put("createPerson","All");
        }
        if("analysisAuth".equals(authType)){
            returnMap.put("projectName",projectsOfAll);
            returnMap.put("createPerson","All");
        }
        return returnMap;
    }


    private Map<String,List<String>> getProjectsByUserId(String userId){
        List<String> projectsOfMine = new ArrayList<>();
        List<String> projectsOfAll = new ArrayList<>();
        List<Map> currentProjectUserList = NewActivitiUtil.getUserByGroup(constructionConfigInfo.getActivitiUrl(),constructionConfigInfo.getCurrentProjectGroup(),"").getBody();
        List<Map> historyProjectUserList = NewActivitiUtil.getUserByGroup(constructionConfigInfo.getActivitiUrl(),constructionConfigInfo.getHistoryProjectGroup(),"").getBody();
        historyProjectUserList.addAll(currentProjectUserList);
        for(Map map : historyProjectUserList){
            Object projectNameObj = map.get("userName");
            if(null==projectNameObj||"".equals(projectNameObj)) continue;
            String projectName = projectNameObj.toString();
            if(!projectsOfAll.contains(projectName)) {
                projectsOfAll.add(projectName);
            }
            if(!projectContainsUserId(map,userId)) continue;
            if(projectsOfMine.contains(projectName)) continue;
            projectsOfMine.add(projectName);
        }
        Map<String,List<String>> map = new HashMap();
        map.put("projectsOfMine",projectsOfMine);
        map.put("projectsOfAll",projectsOfAll);
        return map;
    }

   /* private List<String> getProjects(){
        List<String> projectsOfMine = new ArrayList<>();
        List<Map> currentProjectUserList = NewActivitiUtil.getUserByGroup(constructionConfigInfo.getActivitiUrl(),constructionConfigInfo.getCurrentProjectGroup(),"").getBody();
        List<Map> historyProjectUserList = NewActivitiUtil.getUserByGroup(constructionConfigInfo.getActivitiUrl(),constructionConfigInfo.getHistoryProjectGroup(),"").getBody();
        historyProjectUserList.addAll(currentProjectUserList);
        for(Map map : historyProjectUserList){
            Object projectNameObj = map.get("userName");
            if(null==projectNameObj||"".equals(projectNameObj)) continue;
            String projectName = projectNameObj.toString();
            if(projectsOfMine.contains(projectName)) continue;
            projectsOfMine.add(projectName);
        }
        return projectsOfMine;
    }*/


    private boolean projectContainsUserId(Map map, String userId){
        try{
            Object namesObj = map.get("phone");
            if(null==namesObj||"".equals(namesObj)) return false;
            String[] names = namesObj.toString().split(",");
            for(int i=0;i<names.length;i++){
                if(userId.equals(names[i])){
                    return true;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }






}
