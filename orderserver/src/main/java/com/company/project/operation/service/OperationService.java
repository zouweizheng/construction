package com.company.project.operation.service;


import com.company.project.core.service.AbstractService;
import com.company.project.foundation.model.OpOperation;
import com.company.project.foundation.service.OpOperationService;
import com.company.project.operation.mapper.OperationMapper;
import com.company.project.operation.pojo.OperationPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;


/**
 * Created by Administrator on 2017/10/31.
 */
@Service("OperationService")
public class OperationService extends AbstractService<OperationPojo,OperationMapper>{

    @Autowired
    OpOperationService opOperationService;

    public List<OpOperation> getOpOpration(String projectName){
        OpOperation opOperation = new OpOperation();
        Condition condition = new Condition(opOperation.getClass());
        condition.and().andEqualTo("projectName",projectName).andEqualTo("billOrderId","billNotBinded");
        return opOperationService.findByCondition(condition);
    }


}
