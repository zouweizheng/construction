/*
package com.company.project.core.service;


import com.company.project.construction.pojo.ClaimInfo;
import com.company.project.core.config.MyConfig;
import com.company.project.core.mapper.OrderMapper;
import com.company.project.core.pojo.TaskInfo;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.OrderPojo;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.manager.feignInterface.activiti.ActivitiQueryService;
import com.company.project.manager.feignInterface.activiti.ActivitiUserService;
import com.company.project.manager.feignInterface.activiti.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 *//*

public abstract class AbstractServiceFeignbackup<T,V> implements Service<T,V> {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    TestService.SchedualServiceHi schedualServiceHi;

    @Autowired
    TestService.ActivitiProcessService activitiProcessService;

    @Autowired
    MyConfig myConfig;

    private Class<T> modelClass;    // 当前泛型真实类型的Class
    private Class<V> myMapper;
    private String myMapperName;
    private OrderMapper orderImplMapper;
    //private ActivitiProcessService activitiProcessService;
    private ActivitiQueryService activitiQueryService;
    private ActivitiUserService activitiUserService;

    public AbstractServiceFeignbackup() {
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
        //activitiProcessService = applicationContext.getBean("ActivitiProcessService",ActivitiProcessService.class);
        //activitiQueryService = applicationContext.getBean("ActivitiQueryService",ActivitiQueryService.class);
        //activitiUserService = applicationContext.getBean("ActivitiUserService",ActivitiUserService.class);
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
    public TaskAndOrder insertModel(T model, Map variables , String Tid) {
        String orderId = orderImplMapper.setOrder(model);
        ApiResult apiResult = activitiProcessService.startProcessInstance(orderImplMapper.getProcessDefinitionId(),orderId,variables,Tid);
        return orderAndTaskEncapsulate(orderId,apiResult);
    }

    @Override
    public TaskAndOrder modifyModel(T model, Map inputInfo , String Tid) {
        String orderId = orderImplMapper.setOrder(model);
        ApiResult apiResult = activitiProcessService.completeTask(inputInfo,Tid);
        return orderAndTaskEncapsulate(orderId,apiResult);
    }

    @Override
    public List<TaskAndOrder> getPersonWait(String userId, String Tid) {
        ApiResult apiResult = activitiQueryService.queryAssignedTask(userId,orderImplMapper.getProcessDefinitionId(),Tid);
        return selectOrder(apiResult);
    }


    @Override
    public List<TaskAndOrder> getGroupWait(String userId, String Tid) {
        ApiResult apiResult = activitiUserService.getGroupByUser(userId);
        if(!(0==apiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(apiResult.toString());
        List<Map> groupList = (List<Map>) apiResult.getBody();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        for(Map map : groupList){
            apiResult = activitiQueryService.queryCandidateTask(map.get("id").toString(),orderImplMapper.getProcessDefinitionId(),Tid);
            taskAndOrderList.addAll(selectOrder(apiResult));
        }
        return taskAndOrderList;
    }

    @Override
    public List<TaskAndOrder> getPersonCreate(String userId, String Tid) {
        ApiResult apiResult = activitiQueryService.queryCreatedTask(userId,orderImplMapper.getProcessDefinitionId());
        return selectOrder(apiResult);
    }

    @Override
    public List<TaskAndOrder> getPersonFinish(String userId, String Tid) {
        ApiResult apiResult = activitiQueryService.queryFinishTask(userId,orderImplMapper.getProcessDefinitionId());
        return selectOrder(apiResult);
    }

    @Override
    public List<TaskAndOrder> getNodeWait(String nodeName, String Tid) {
        ApiResult apiResult = activitiQueryService.queryNodeTask(nodeName,orderImplMapper.getProcessDefinitionId(),Tid);
        return selectOrder(apiResult);
    }

    @Override
    public List<TaskAndOrder> getNodePersonWait(String nodeName, String userId, String Tid) {
        ApiResult apiResult = activitiQueryService.queryNodeTask(nodeName,orderImplMapper.getProcessDefinitionId(),userId,Tid);
        return selectOrder(apiResult);
    }

    @Override
    public List<TaskAndOrder> getNodeFinish(String nodeName, String Tid) {
        ApiResult apiResult = activitiQueryService.queryFinishNode(nodeName,orderImplMapper.getProcessDefinitionId());
        return selectOrder(apiResult);
    }

    @Override
    public TaskInfo claim(ClaimInfo claimInfo, String Tid) {
        ApiResult<TaskInfo> apiResult = activitiProcessService.claimTask((Map)claimInfo,Tid);
        if(0==apiResult.getErrcode()) return apiResult.getBody();
        return null;
    }

    @Override
    public TaskInfo commit(ClaimInfo claimInfo, String Tid) {
        ApiResult<TaskInfo> apiResult = activitiProcessService.completeTask((Map)claimInfo,Tid);
        if(0==apiResult.getErrcode()) return apiResult.getBody();
        return null;
    }

    @Override
    public TaskInfo commitAndClaim(ClaimInfo claimInfo, String Tid) {
        ApiResult<TaskInfo> apiResult = activitiProcessService.completeandclaimTask((Map)claimInfo,Tid);
        if(0==apiResult.getErrcode()) return apiResult.getBody();
        return null;
    }

    */
/**
     * 根据流程列表获取工单和流程信息
     * @param taskList
     * @return
     *//*

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

    private TaskInfo getTaskInfoTest(){
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setBusinessKey("1");
        return taskInfo;
    }

    private TaskAndOrder orderAndTaskEncapsulate(String orderId , ApiResult taskResult ){
        if(!(0==taskResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(taskResult.toString());
        TaskAndOrder taskAndOrder = new TaskAndOrder();
        taskAndOrder.setOrderId(orderId);
        taskAndOrder.setTaskInfo((TaskInfo)taskResult.getBody());
        return taskAndOrder;
    }

    private List<TaskAndOrder> selectOrder(ApiResult<List<TaskInfo>> listApiResult){
        if(!(0==listApiResult.getErrcode())) throw new com.company.project.foundation.core.ServiceException(listApiResult.toString());
        List<TaskInfo> taskInfoList = listApiResult.getBody();
        List<TaskAndOrder> taskAndOrderList = new ArrayList<>();
        TaskAndOrder taskAndOrder = new TaskAndOrder();
        for(TaskInfo taskInfo : taskInfoList){
            String orderId = taskInfo.getBusinessKey();
            OrderPojo orderPojo = (OrderPojo) orderImplMapper.getOrderInfo(orderId);
            taskAndOrder.setOrderPojo(orderPojo);
            taskAndOrder.setOrderId(orderId);
            taskAndOrder.setTaskInfo(taskInfo);
            taskAndOrderList.add(taskAndOrder);
        }
        return taskAndOrderList;
    }

}
*/
