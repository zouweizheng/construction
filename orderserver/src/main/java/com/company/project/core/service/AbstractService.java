package com.company.project.core.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.foundation.dao.MixMapper;
import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.mapper.OrderMapper;
import com.company.project.core.pojo.*;
import com.company.project.core.util.MapReflect;
import com.company.project.core.util.MapToObjectUtil;
import com.company.project.core.util.NewActivitiUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T,V> implements Service<T,V> {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MixMapper mixMapper;



    private Class<T> modelClass;    // 当前泛型真实类型的Class
    private Class<V> myMapper;
    private String myMapperName;
    private OrderMapper orderImplMapper;
    private OrderConfigInfo orderConfigInfo;
    private String activitiUrl;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String agentPerson;

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
        myMapper = (Class<V>)  pt.getActualTypeArguments()[1];
        String myMapperName = myMapper.getName();
        String[] split = myMapperName.split("\\.");
        this.myMapperName = split[split.length-1];
    }

    @PostConstruct
    public void init(){
        orderImplMapper = applicationContext.getBean(this.myMapperName,OrderMapper.class);
        orderConfigInfo = applicationContext.getBean(MapReflect.getOrderConfigReflect(this.myMapperName),OrderConfigInfo.class);
        //orderConfigInfo = applicationContext.getBean("ConstructionConfigInfo",OrderConfigInfo.class);
        activitiUrl = orderConfigInfo.getActivitiUrl();
        processDefinitionId = orderConfigInfo.getProcessDefinitionId();
        processDefinitionKey = orderConfigInfo.getProcessDefinitionKey();
        agentPerson = orderConfigInfo.getAgentPerson();
    }



    @Override
    public String getNameTest(String fieldName ,T model){

        try {
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object modelFieldNameValue = field.get(model);
            return modelFieldNameValue.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMethodTest(String mothodName, T model) {
        try {
            Method m1 = modelClass.getDeclaredMethod(mothodName);
            String msg = m1.invoke(model).toString();
            System.out.println(msg);
            return msg;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String setOrder(OrderPojo orderPojo) {
        String orderId = orderImplMapper.setOrder(orderPojo);
        return orderId;
    }

    @Override
    public TaskAndOrder insertModel(T model, Map variables , String Tid) throws Exception {
        String orderId = orderImplMapper.setOrder(model);
        variables.put("orderId",orderId);
        ApiResult<TaskInfo> apiResult = NewActivitiUtil.startProcessInstance(activitiUrl,processDefinitionKey,orderId,variables,Tid);
        return orderAndTaskEncapsulate(orderId,apiResult);
    }

    @Override
    public TaskAndOrder modifyModel(T model, Map inputInfo , String Tid) {
        /*String orderId = orderImplMapper.setOrder(model);
        ApiResult apiResult = NewActivitiUtil.completeTask()
        ApiResult apiResult = activitiProcessService.completeTask(inputInfo,Tid);
        return orderAndTaskEncapsulate(orderId,apiResult);*/
        return null;
    }

    @Override
    public List<TaskAndOrder> getPersonWait(String userId, String Tid,String searchWord) {
        ApiResult apiResult = NewActivitiUtil.queryAssignedTask(activitiUrl,userId,agentPerson,processDefinitionId,Tid);
        if(!(0==apiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(apiResult.toString());
        return selectOrder(apiResult,searchWord);
    }

    @Override
    public PageInfo getPersonWait(String userId, String Tid,String searchWord,Integer page,Integer size) {
        ApiResult apiResult = NewActivitiUtil.queryAssignedTask(activitiUrl,userId,agentPerson,processDefinitionId,Tid);
        if(!(0==apiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(apiResult.toString());
        return selectOrder(apiResult,searchWord,page,size);
    }


    @Override
    public List<TaskAndOrder> getGroupWait(String userId, String Tid,String searchWord) {
        ApiResult apiResult = NewActivitiUtil.getGroupByUser(activitiUrl,userId,Tid);
        if(!(0==apiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(apiResult.toString());
        List<Map> groupList = (List<Map>) apiResult.getBody();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        for(Map map : groupList){
            apiResult = NewActivitiUtil.queryCandidateTask(activitiUrl,map.get("id").toString(),processDefinitionId,Tid);
            taskAndOrderList.addAll(selectOrder(apiResult,searchWord));
        }
        return taskAndOrderList;
    }
    @Override
    public PageInfo getGroupWait(String userId, String Tid,String searchWord,Integer page,Integer size) {
        ApiResult apiResult = NewActivitiUtil.getGroupByUser(activitiUrl,userId,Tid);
        if(!(0==apiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(apiResult.toString());
        List<Map> groupList = (List<Map>) apiResult.getBody();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        List<Map> taskInfoList = new ArrayList<>();
        for(Map map : groupList){
            apiResult = NewActivitiUtil.queryCandidateTask(activitiUrl,map.get("id").toString(),processDefinitionId,Tid);
            if(null==apiResult.getBody()) continue;
            taskInfoList.addAll((List<Map>) apiResult.getBody());
        }
        return orderImplMapper.getOrderList(taskInfoList,searchWord,page,size);
    }


    @Override
    public List<TaskAndOrder> getPersonCreate(String userId, String Tid) {
        /*ApiResult apiResult = NewActivitiUtil.que
        ApiResult apiResult = activitiQueryService.queryCreatedTask(userId,orderImplMapper.getProcessDefinitionId());
        return selectOrder(apiResult);*/
        return null;
    }

    @Override
    public List<TaskAndOrder> getPersonFinish(String userId, String Tid,String searchWord) {
        ApiResult apiResult = NewActivitiUtil.queryFinishTask(activitiUrl,userId,processDefinitionId,Tid);
        return selectOrder(apiResult,searchWord);
    }

    @Override
    public PageInfo getPersonFinish(String userId, String Tid, String searchWord, Integer page, Integer size) {
        ApiResult apiResult = NewActivitiUtil.queryFinishTask(activitiUrl,userId,processDefinitionId,Tid);
        if(!(0==apiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(apiResult.toString());
        return selectOrder(apiResult,searchWord,page,size);
    }

    @Override
    public List<TaskAndOrder> getNodeWait(String nodeName, String Tid) {
        ApiResult apiResult = NewActivitiUtil.queryNodeTask(activitiUrl,nodeName,processDefinitionId,Tid);
        return selectOrder(apiResult,"");
    }

    @Override
    public List<TaskAndOrder> getNodePersonWait(String nodeName, String userId, String Tid) {
        /*ApiResult apiResult = activitiQueryService.queryNodeTask(nodeName,orderImplMapper.getProcessDefinitionId(),userId,Tid);
        return selectOrder(apiResult);*/
        return null;
    }

    @Override
    public Map getOrderDetail(String orderId, String taskId,String userId ,String Tid) {
        Map map = new HashMap();
        if(!"0".equals(taskId)){
            Map taskStatus = NewActivitiUtil.getStatus(activitiUrl, taskId,userId,Tid).getBody();
            map.put("taskInfo",taskStatus);
        }
        OrderPojo orderPojo = (OrderPojo) orderImplMapper.getOrderInfo(orderId);
        map.put("orderPojo",orderPojo);
        return map;
    }

    @Override
    public List<TaskAndOrder> getNodeFinish(String nodeName, String Tid) {
        ApiResult apiResult = NewActivitiUtil.queryFinishNode(activitiUrl,nodeName,processDefinitionId,Tid);
        return selectOrder(apiResult,"");
    }

    @Override
    public Boolean claim(ClaimInfo claimInfo, String Tid) {
        ApiResult<TaskInfo> apiResult = NewActivitiUtil.claimTask(activitiUrl,claimInfo.getTaskId(),claimInfo.getUserId(),Tid);
        return true;
    }

    @Override
    public TaskInfo commit(ClaimInfo claimInfo, String Tid) throws Exception {
        ApiResult<Map> apiResult = NewActivitiUtil.completeTask(activitiUrl,claimInfo.getTaskId(),claimInfo.getUserId(),claimInfo.getVariables(),Tid);
        if(0==apiResult.getErrcode()){
            Map taskMapResult = apiResult.getBody();
            TaskInfo taskInfo = new TaskInfo();
            taskInfo = MapToObjectUtil.mapperObj(taskMapResult,taskInfo.getClass());
            orderImplMapper.setOrderStatus(taskInfo.getOrderId(),taskInfo.getName());
            if(orderImplMapper.doAfterCommit(claimInfo,taskInfo)) return taskInfo;
        }
        return null;
    }

    @Override
    public TaskInfo commitAndClaim(ClaimInfo claimInfo, String Tid) {
        ApiResult<TaskInfo> apiResult = NewActivitiUtil.completeandclaimTask(activitiUrl, JSON.parseObject(JSONObject.toJSONString(claimInfo)),Tid);
        if(0==apiResult.getErrcode()) return apiResult.getBody();
        return null;
    }

    /**
     * 根据输入的条件获取所有工单
     * @return
     */
    @Override
    public List<TaskAndOrder> getOrderByTime(Date startTime , Date endTime) {
        List<OrderPojo> orderPojoList =  orderImplMapper.getOrderByTime(startTime,endTime);
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        for(OrderPojo orderPojo : orderPojoList){
            TaskAndOrder taskAndOrder = new TaskAndOrder();
            taskAndOrder.setOrderId(orderPojo.getId());
            taskAndOrder.setOrderPojo(orderPojo);
            taskAndOrderList.add(taskAndOrder);
        }
        return taskAndOrderList;
    }

    /**
     * 根据输入的条件获取所有工单
     * @return
     */
    @Override
    public PageInfo getOrders(Map queryCriteria, Integer page, Integer size) {
        PageInfo orderPojoList = orderImplMapper.getOrderListByQueryCriteria(queryCriteria,page,size);
        return orderPojoList;
    }

    /**
     * 根据流程列表获取工单和流程信息
     * @param taskList
     * @return
     */
    private List<Map> getOrderAndTaskList(List<TaskInfo> taskList ){
        List<Map> returnList = new ArrayList<>();
        Map singalResult = new HashMap();
        for(TaskInfo taskInfo : taskList){
            OrderPojo orderInfo =  (OrderPojo) orderImplMapper.getOrderInfo(taskInfo.getBusinessKey().toString().trim());
            singalResult.put("taskInfo", taskInfo);
            singalResult.put("orderInfo",orderInfo);
            returnList.add(singalResult);
        }
        return returnList;
    }



    private TaskAndOrder orderAndTaskEncapsulate(String orderId , ApiResult<TaskInfo> taskResult ) throws Exception {
        if(!(0==taskResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(taskResult.toString());
        TaskAndOrder taskAndOrder = new TaskAndOrder();
        taskAndOrder.setOrderId(orderId);
        Map taskInfoMap = (Map) taskResult.getBody();
        TaskInfo taskInfo = new TaskInfo();
        taskInfo = MapToObjectUtil.mapperObj(taskInfoMap,taskInfo.getClass());
        orderImplMapper.setProInsId(orderId,taskInfo.getProcessInstanceId());
        orderImplMapper.setOrderStatus(orderId,taskInfo.getName().toString());
        taskAndOrder.setTaskInfo(taskInfo);
        return taskAndOrder;
    }

    //之前的做法为单个循环查找
    /*private List<TaskAndOrder>  selectOrder(ApiResult<List> listApiResult){
        if(!(0==listApiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(listApiResult.toString());
        List<Map> taskInfoList = listApiResult.getBody();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        TaskInfo taskInfo = new TaskInfo();
        for(Map map : taskInfoList){
            TaskAndOrder taskAndOrder = new TaskAndOrder();
            try {
                taskInfo = MapToObjectUtil.mapperObj(map,taskInfo.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String orderId = taskInfo.getBusinessKey();
            if("".equals(orderId)||null==orderId) continue;
            OrderPojo orderPojo = (OrderPojo) orderImplMapper.getOrderInfo(orderId);
            if(null==orderPojo) continue;
            taskAndOrder.setOrderPojo(orderPojo);
            taskAndOrder.setOrderId(orderId);
            taskAndOrder.setTaskInfo(taskInfo);
            taskAndOrderList.add(taskAndOrder);
        }
        return taskAndOrderList;
    }*/

    private PageInfo  selectOrder(ApiResult<List> listApiResult, String searchWord,Integer page,Integer size){
        if(!(0==listApiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(listApiResult.toString());
        List<Map> taskInfoList = listApiResult.getBody();

        PageInfo orderPojoList = orderImplMapper.getOrderList(taskInfoList,searchWord,page,size);

        return orderPojoList;
    }


    private List<TaskAndOrder>  selectOrder(ApiResult<List> listApiResult, String searchWord){
        if(!(0==listApiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(listApiResult.toString());
        List<Map> taskInfoList = listApiResult.getBody();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        TaskInfo taskInfo = new TaskInfo();

        //1、获取所有orderId，2、封装单独的taskInfo
        List<String> orderIds = new ArrayList<>();
        Map<String,TaskInfo> taskInfoMap = new HashMap<>();
        for(Map map : taskInfoList){
            try {
                taskInfo = MapToObjectUtil.mapperObj(map,taskInfo.getClass());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            String orderId = taskInfo.getBusinessKey();
            if("".equals(orderId)||null==orderId) continue;
            orderIds.add(taskInfo.getBusinessKey());
            if(taskInfoMap.containsKey(orderId)) continue;
            taskInfoMap.put(orderId,taskInfo);
        }

        //查找orderPojoList并封装
        List<OrderPojo> orderPojoList = orderImplMapper.getOrderList(orderIds,searchWord);
        for(OrderPojo orderPojo : orderPojoList){
            TaskAndOrder taskAndOrder = new TaskAndOrder();
            String orderId = orderPojo.getId();
            taskAndOrder.setOrderPojo(orderPojo);
            taskAndOrder.setOrderId(orderId);
            taskAndOrder.setTaskInfo(taskInfoMap.get(orderId));
            taskAndOrderList.add(taskAndOrder);
        }

        return taskAndOrderList;
    }

    public Map getTotalInfo(Map inputMap){
        Map totalInfo = mixMapper.getTotalNum(inputMap);
        return totalInfo;
    }

    public List getTotalInfoGroupByWorkType(Map inputMap){
        List totalInfoList = mixMapper.getTotalInfoGroupByWorkType(inputMap);
        return totalInfoList;
    }
    public List getAllCon(Map inputMap){
        List totalInfoList = mixMapper.getAllCon(inputMap);
        return totalInfoList;
    }


}
