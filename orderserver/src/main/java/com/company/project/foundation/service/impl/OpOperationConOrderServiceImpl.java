package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.OpOperationConOrderMapper;
import com.company.project.foundation.model.OpOperationConOrder;
import com.company.project.foundation.service.OpOperationConOrderService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/12/19.
 */
@Service
@Transactional
public class OpOperationConOrderServiceImpl extends AbstractService<OpOperationConOrder> implements OpOperationConOrderService {
    @Resource
    private OpOperationConOrderMapper opOperationConOrderMapper;

}
