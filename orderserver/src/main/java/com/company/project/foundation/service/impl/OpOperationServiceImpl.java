package com.company.project.foundation.service.impl;

import com.company.project.foundation.dao.OpOperationMapper;
import com.company.project.foundation.model.OpOperation;
import com.company.project.foundation.service.OpOperationService;
import com.company.project.foundation.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/07/10.
 */
@Service
@Transactional
public class OpOperationServiceImpl extends AbstractService<OpOperation> implements OpOperationService {
    @Resource
    private OpOperationMapper opOperationMapper;

}
